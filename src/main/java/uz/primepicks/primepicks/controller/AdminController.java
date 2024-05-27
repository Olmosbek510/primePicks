package uz.primepicks.primepicks.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.primepicks.primepicks.entity.Category;
import uz.primepicks.primepicks.entity.Income;
import uz.primepicks.primepicks.entity.OrderProduct;
import uz.primepicks.primepicks.entity.Product;
import uz.primepicks.primepicks.projection.BalanceProjection;
import uz.primepicks.primepicks.projection.ProductProfitProjection;
import uz.primepicks.primepicks.projection.ProductProjection;
import uz.primepicks.primepicks.projection.SalesReportProjection;
import uz.primepicks.primepicks.repo.*;

import java.util.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final IncomeRepo incomeRepo;
    private final OrderProductRepo orderProductRepo;
    @GetMapping
    public String admin(Model model,
                        @RequestParam(name = "categoryId", required = false) UUID categoryId,
                        @RequestParam(name = "productId", required = false) UUID productId,
                        HttpSession session,
                        @RequestParam(name = "pattern", required = false) String pattern,
                        @RequestParam(name = "product-pattern", required = false) String prodPattern,
                        @RequestParam(name = "want", required = false) String want
    ) {
        List<Category> categories = categoryRepo.findAll();
        if (!Objects.equals(pattern, null)) {
            categories = categoryRepo.findAllByNameContainingIgnoreCase(pattern);
        }
        model.addAttribute("categories", categories);
        Category category = new Category();
        category.setId(UUID.randomUUID());
        List<Product> products = new ArrayList<>();
        if (categoryId != null) {
            session.removeAttribute("currentProduct");
            category = categoryRepo.findById(categoryId).get();
            session.setAttribute("currentCategory", category);
        } else if (session.getAttribute("currentCategory") != null) {
            category = (Category) session.getAttribute("currentCategory");
        }
        products = productRepo.findALlByCategoryIdOrderByName(category.getId());
        model.addAttribute("category", category);
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
        } else if (session.getAttribute("currentProduct") != null) {
            product = (Product) session.getAttribute("currentProduct");
        }
        ProductProjection productProjection = productRepo.findProductProjById(product.getId());
        model.addAttribute("productProj", productProjection);
        model.addAttribute("product", product);
        if (Objects.equals(want, "income")) {
            List<Income> incomeList = incomeRepo.findAllByProductId(product.getId());
            model.addAttribute("incomes", incomeList);
        } else if (Objects.equals(want, "sales")) {
            List<OrderProduct> orderProductList = orderProductRepo.findOrderProductByProductId(product.getId());
            model.addAttribute("orderProducts", orderProductList);
        } else {
            want = "";
        }
        model.addAttribute("want", want);
        return "/admin/admin";
    }

    @GetMapping("/report")
    public String report(
            @RequestParam(name = "want", required = false) String want,
            Model model
    ) {
        want = want == null ? "" : want;
        System.out.println("want: " + want);
        model.addAttribute("want", want);
        switch (want) {
            case "sales" -> {
                List<SalesReportProjection> report = productRepo.getReport();
                model.addAttribute("reports", report);
            }
            case "balance" -> {
                List<Product> products = productRepo.findAll();
                List<BalanceProjection> balanceProjections = new ArrayList<>();
                for (Product product : products) {
                    balanceProjections.add(new BalanceProjection(product.getName(), productRepo.getRemainingById(product.getId()) == null ? 0 : productRepo.getRemainingById(product.getId())));
                    balanceProjections.sort(Comparator.comparingInt(BalanceProjection::remaining));
                }
                model.addAttribute("balance", balanceProjections);
            }
            case "profit" -> {
                List<SalesReportProjection> report = productRepo.getReport();
                List<ProductProfitProjection> profits = new ArrayList<>();
                for (SalesReportProjection salesReportProjection : report) {
                    profits.add(productRepo.getProfitByProductId(salesReportProjection.getId(), salesReportProjection.getAmount()));
                }
                model.addAttribute("profits", profits);
            }
        }
        return "admin/report";
    }
}
