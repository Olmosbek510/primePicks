package uz.primepicks.primepicks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.primepicks.primepicks.entity.Income;
import uz.primepicks.primepicks.entity.Product;
import uz.primepicks.primepicks.repo.IncomeRepo;
import uz.primepicks.primepicks.repo.ProductRepo;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("income")
@RequiredArgsConstructor
public class IncomeController {
    private final ProductRepo productRepo;
    private final IncomeRepo incomeRepo;

    @GetMapping("/make/{productId}")
    public String make(@PathVariable("productId") UUID productId, Model model){
        Optional<Product> product = productRepo.findById(productId);
        product.ifPresent(value -> model.addAttribute("product", value));
        return "/income/makeIncome";
    }

    @PostMapping("create/{id}")
    public String make(@PathVariable("id") UUID uuid, @ModelAttribute Income income){
        Optional<Product> product = productRepo.findById(uuid);
        product.ifPresent(income::setProduct);
        incomeRepo.save(income);
        return "redirect:/admin?want=income";
    }
}
