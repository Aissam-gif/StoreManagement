package net.codejava.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.codejava.model.Product;
import net.codejava.service.CartItemService;
import net.codejava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller @RequiredArgsConstructor @Slf4j
public class AppController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private final CartItemService cartItemService;


    @GetMapping("/home")
    public String viewHomePage(Model model) {
        return showProductsByPage(model,1);
    }

    @GetMapping("/home/{pageNumber}")
    public String showProductsByPage(Model model, @PathVariable("pageNumber") int currentPage){
        if(currentPage>0){
            Page<Product> page = productService.findPage(currentPage);
            if(!page.isEmpty()){
                int totalPages = page.getTotalPages();
                long totalItems = page.getTotalElements();
                List<Product> productList = page.getContent();
                model.addAttribute("cartItemsCount", cartItemService.getItemsCount());
                model.addAttribute("currentPage", currentPage);
                model.addAttribute("totalPages", totalPages);
                model.addAttribute("totalItems", totalItems);
                model.addAttribute("products", productList);
                log.info("Product List {}", productList.size());
                return "home";
            }
        }
        return "redirect:/home";
    }
    @GetMapping("/profile")
    public String viewProfilePage() {
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile() {
        return "";
    }

    @GetMapping("/403")
    public String viewDeniedPage() {
        return "redirect:/home";
    }

    @GetMapping("/404")
    public String viewNotFoundPage() {
        return "redirect:/home";
    }

}
