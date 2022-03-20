package net.codejava.controller;


import lombok.RequiredArgsConstructor;
import net.codejava.model.Category;
import net.codejava.model.User;
import net.codejava.repo.CategoryRepository;
import net.codejava.service.CategoryService;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/products")
    public String showProducts(){
        return "admin_products";
    }

    @GetMapping("/users")
    public String showManagers(Model model)
    {
        model.addAttribute("users", userService.getUsers());
        return "clients/index";
    }

    @GetMapping("/categories")
    public String addCategorie(Model model) {
        model.addAttribute("categories", categoryService.getCategories());
        return "category/index";
    }

    @PostMapping("/processCategory")
    public String processCategory(RedirectAttributes ra, @ModelAttribute("category") String category) {
        // TODO:
        categoryService.saveCategory(category);
        ra.addFlashAttribute("message","Category " + category + " saved Succesfly");
        return "redirect:categories";
    }

    @GetMapping("/saveCategory")
    public String saveCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category/category_form";
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
