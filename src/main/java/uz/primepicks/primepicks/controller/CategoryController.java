package uz.primepicks.primepicks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.primepicks.primepicks.entity.Category;
import uz.primepicks.primepicks.repo.CategoryRepo;

@Controller
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepo categoryRepo;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("add")
    public String add(){
        return "/category/addCategory";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public String add(@ModelAttribute Category category){
        categoryRepo.save(category);
        return "redirect:/admin";
    }
}
