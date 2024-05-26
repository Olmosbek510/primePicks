package uz.primepicks.primepicks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.primepicks.primepicks.entity.Attachment;
import uz.primepicks.primepicks.entity.AttachmentContent;
import uz.primepicks.primepicks.entity.Category;
import uz.primepicks.primepicks.entity.Product;
import uz.primepicks.primepicks.repo.AttachmentContentRepo;
import uz.primepicks.primepicks.repo.AttachmentRepo;
import uz.primepicks.primepicks.repo.CategoryRepo;
import uz.primepicks.primepicks.repo.ProductRepo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final AttachmentRepo attachmentRepo;
    private final AttachmentContentRepo attachmentContentRepo;

    @GetMapping("add")
    public String add(Model model) {
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "product/addProduct";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Product product,
                      @RequestParam(name = "product-photo") MultipartFile photo,
                      @RequestParam(name = "categoryId") UUID catId) throws IOException {
        Optional<Category> category = categoryRepo.findById(catId);
        category.ifPresent(product::setCategory);
        Attachment attachment = attachmentRepo.save(Attachment.builder()
                .type(photo.getContentType())
                .build());
        attachmentContentRepo.save(AttachmentContent.builder()
                .attachment(attachment)
                .content(photo.getBytes())
                .build());
        product.setPhoto(attachment);
        productRepo.save(product);
        return "redirect:/admin";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID productId, @ModelAttribute Product product){
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(optionalProduct.isPresent()){
            System.out.println("new Product: " + product);
            Product product1 = optionalProduct.get();
            product1.setName(product.getName());
            product1.setRetailPrice(product.getRetailPrice());
            productRepo.save(product1);
        }
        return "redirect:/admin?want=''";

    }
}
