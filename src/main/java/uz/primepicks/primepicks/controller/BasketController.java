package uz.primepicks.primepicks.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.primepicks.primepicks.entity.Basket;
import uz.primepicks.primepicks.entity.BasketProduct;
import uz.primepicks.primepicks.repo.ProductRepo;

import java.util.Objects;

@Controller
@RequestMapping("basket")
@RequiredArgsConstructor
public class BasketController {
    private final ProductRepo productRepo;

    @GetMapping("/increase")
    public String increase(HttpSession session) {
        Object o = session.getAttribute("chosenProduct");
        if (o != null) {
            BasketProduct basketProduct = (BasketProduct) o;
            Integer remaining = productRepo.getRemainingById(basketProduct.getProduct().getId());
            if (remaining != null && remaining > basketProduct.getAmount()) {
                basketProduct.setAmount(basketProduct.getAmount() + 1);
            }

            session.setAttribute("chosenProduct", basketProduct);
        }
        return "redirect:/user";
    }

    @GetMapping("/decrease")
    public String decrease(HttpSession session) {
        Object o = session.getAttribute("chosenProduct");
        if (o != null) {
            BasketProduct basketProduct = (BasketProduct) o;
            if (basketProduct.getAmount() > 1) {
                basketProduct.setAmount(basketProduct.getAmount() - 1);
            }
        }
        return "redirect:/user";
    }

    @PostMapping("add")
    public String add(HttpSession session) {
        Object basketObj = session.getAttribute("basket");
        Object productObj = session.getAttribute("chosenProduct");
        if (basketObj != null && productObj != null) {
            BasketProduct basketProduct = (BasketProduct) productObj;
            Basket basket = (Basket) basketObj;

            if (basketProduct.getAmount() != 0) {
                for (BasketProduct product : basket.getProducts()) {
                    if (product.getProduct().getId().equals(basketProduct.getProduct().getId())) {
                        product.setAmount(product.getAmount() + basketProduct.getAmount());
                        return "redirect:/user";
                    }
                }
                basket.getProducts().add(basketProduct);
            }
            session.removeAttribute("chosenProduct");
        }
        return "redirect:/user";
    }
}
