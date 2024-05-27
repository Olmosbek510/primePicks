package uz.primepicks.primepicks.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.primepicks.primepicks.entity.Basket;
import uz.primepicks.primepicks.entity.BasketProduct;
import uz.primepicks.primepicks.entity.Category;
import uz.primepicks.primepicks.entity.Product;
import uz.primepicks.primepicks.projection.ProductProjection;
import uz.primepicks.primepicks.repo.CategoryRepo;
import uz.primepicks.primepicks.repo.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping()
    public String user(Model model,
                       @RequestParam(name = "categoryId", required = false) UUID categoryId,
                       @RequestParam(name = "productId", required = false) UUID productId,
                       HttpSession session,
                       @RequestParam(name = "pattern", required = false) String pattern,
                       @RequestParam(name = "product-pattern", required = false) String prodPattern
    ) {
        List<Category> categories = categoryRepo.findAll();
        if (!Objects.equals(pattern, null)) {
            categories = categoryRepo.findAllByNameContainingIgnoreCase(pattern);
            session.removeAttribute("currentCategory");
        }
        model.addAttribute("categories", categories);
        Category category = new Category();
        category.setId(UUID.randomUUID());
        List<Product> products;
        if (categoryId != null) {
            session.removeAttribute("currentProduct");
            category = categoryRepo.findById(categoryId).get();
            session.setAttribute("currentCategory", category);
        } else if (session.getAttribute("currentCategory") != null) {
            category = (Category) session.getAttribute("currentCategory");
        }
        model.addAttribute("category", category);
        products = productRepo.findALlByCategoryIdOrderByName(category.getId());
        if (!products.isEmpty()) {
            if (prodPattern != null) {
                products.removeIf(product -> !product.getName().toLowerCase().contains(prodPattern.toLowerCase()));
            }
            model.addAttribute("products", products);
        }

        Product product = new Product();
        product.setId(UUID.randomUUID());
        if (productId != null) {
            product = productRepo.findById(productId).get();
            session.setAttribute("currentProduct", product);
            session.removeAttribute("chosenProduct");
        } else if (session.getAttribute("currentProduct") != null) {
            product = (Product) session.getAttribute("currentProduct");
        }
        ProductProjection productProjection = productRepo.findProductProjById(product.getId());
        model.addAttribute("productProj", productProjection);
        model.addAttribute("product", product);

        BasketProduct basketProduct = BasketProduct.builder()
                .product(product)
                .build();
        Integer remaining = productRepo.getRemainingById(product.getId());
        basketProduct.setAmount(remaining == 0 ? 0 : 1);
        model.addAttribute("remaining", remaining);
        if (productProjection != null) {
            basketProduct.setBase64Photo(productProjection.base64Photo());
        }

        if (session.getAttribute("chosenProduct") != null) {
            basketProduct = (BasketProduct) session.getAttribute("chosenProduct");
        } else {
            if (productProjection != null) {
                session.setAttribute("chosenProduct", basketProduct);
            }
        }
        model.addAttribute("chosenProduct", basketProduct);

        Basket basket;
        if (session.getAttribute("basket") == null) {
            basket = new Basket();
            session.setAttribute("basket", basket);
        } else {
            basket = (Basket) session.getAttribute("basket");
        }
        model.addAttribute("basket", basket);
        int sum = basket.getProducts().stream().mapToInt(product1 -> product1.getAmount() * product1.getProduct().getRetailPrice()).sum();
        model.addAttribute("totalPrice", sum);
        return "user/user";
    }
}
