package uz.primepicks.primepicks.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.primepicks.primepicks.entity.Basket;
import uz.primepicks.primepicks.entity.BasketProduct;
import uz.primepicks.primepicks.entity.Order;
import uz.primepicks.primepicks.entity.OrderProduct;
import uz.primepicks.primepicks.repo.OrderProductRepo;
import uz.primepicks.primepicks.repo.OrderRepo;
import uz.primepicks.primepicks.repo.UserRepo;

import java.security.Principal;

@Controller
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;
    private final OrderProductRepo orderProductRepo;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("make")
    public String make(Principal principal, HttpSession session) {
        Object o = session.getAttribute("basket");
        if (o != null) {
            Order order = orderRepo.save(Order.builder()
                    .user(userRepo.findByUsername(principal.getName()))
                    .build());
            Basket basket = (Basket) o;
            for (BasketProduct product : basket.getProducts()) {
                orderProductRepo.save(OrderProduct.builder()
                        .order(order)
                        .amount(product.getAmount())
                        .product(product.getProduct())
                        .build());
            }
            session.removeAttribute("basket");
        }
        return "redirect:/user";
    }
}
