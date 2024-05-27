package uz.primepicks.primepicks.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.primepicks.primepicks.entity.*;
import uz.primepicks.primepicks.projection.OrderProductProjection;
import uz.primepicks.primepicks.repo.OrderProductRepo;
import uz.primepicks.primepicks.repo.OrderRepo;
import uz.primepicks.primepicks.repo.UserRepo;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;
    private final OrderProductRepo orderProductRepo;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public String orders(Principal principal, Model model){
        User user = userRepo.findByUsername(principal.getName());
        List<Order> orders = orderRepo.findAllByUserId(user.getId());
        model.addAttribute("orders", orders);
        return "user/orders";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("make")
    public String make(Principal principal, HttpSession session) {
        Object o = session.getAttribute("basket");
        if (o != null) {
            Basket basket = (Basket) o;
            if (!basket.getProducts().isEmpty()) {
                Order order = orderRepo.save(Order.builder()
                        .user(userRepo.findByUsername(principal.getName()))
                        .build());
                for (BasketProduct product : basket.getProducts()) {
                    orderProductRepo.save(OrderProduct.builder()
                            .order(order)
                            .amount(product.getAmount())
                            .product(product.getProduct())
                            .build());
                }
            }
            session.removeAttribute("basket");
        }
        return "redirect:/user";
    }

    @GetMapping("/info")
    public String getInfo(@RequestParam(name = "orderId")UUID orderId, Model model){
        List<OrderProductProjection> orderProducts = orderProductRepo.findAllByOrderId(orderId);
        model.addAttribute("orderProducts", orderProducts);
        return "/user/orderDetails";
    }
}
