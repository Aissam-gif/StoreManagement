package net.codejava.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.codejava.model.Category;
import net.codejava.model.Product;
import net.codejava.service.CategoryService;
import net.codejava.service.ProductService;
import net.codejava.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.util.List;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final ProductService productService;


    @GetMapping("/products")
    public String showProducts(Model model){
        return showProductsByPage(model,1);
    }

    @GetMapping("/products/{pageNumber}")
    public String showProductsByPage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Product> page = productService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Product> productList = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("products", productList);
        log.info("Product List {}", productList.size());
        return "admin_products";
    }

    @PostMapping("/products/save")
    public String addProduct(){
        return "admin_products";
    }


    @GetMapping("/products/update/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model){
        return "admin_products";
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Product product){
        return "admin_products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes ra){
        try {
            productService.deleteProduct(id);
            ra.addFlashAttribute("message", "Product deleted Successfly");
        } catch(Exception e) {
            ra.addFlashAttribute("messageErr", "Something went wrong, Couldn't Delete Product");
        }
        return "redirect:/admin/products";
    }


    @GetMapping("/users")
    public String showManagers(Model model)
    {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/categories")
    public String addCategory(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        return "category/categories";
    }

    @PostMapping("/processCategory")
    public String processCategory(RedirectAttributes ra, @ModelAttribute("category") String category) {
        // TODO:
        categoryService.saveCategory(category);
        ra.addFlashAttribute("message","Category " + category + " saved Succesfly");
        return "redirect:categories";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            categoryService.deleteCategory(id);
            ra.addFlashAttribute("message", "Category deleted Successfly");
        } catch(Exception e) {
            ra.addFlashAttribute("messageErr", "Something went wrong, Couldn't Delete Category");
        }
        return "redirect:/admin/categories";
    }
}
