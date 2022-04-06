package net.codejava.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.codejava.config.CustomUserDetails;
import net.codejava.model.Product;
import net.codejava.model.User;
import net.codejava.service.CartItemService;
import net.codejava.service.ProductService;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller @RequiredArgsConstructor @Slf4j
public class AppController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private final CartItemService cartItemService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

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
            }
            return "home";
        }
        return "redirect:/home";
    }
    @GetMapping("/profile")
    public String viewProfilePage(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        log.info("User 1 "+ customUserDetails.getUser().toString());
        model.addAttribute("user",customUserDetails.getUser());
        return "profile";
    }


    @PostMapping("/profile/save")
    public String saveProfile(@ModelAttribute User user, RedirectAttributes ra,@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        user.setEnabled(true);
        user.setRole(customUserDetails.getUser().getRole());
        if (!user.getPassword().equals(customUserDetails.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.saveUser(user);
        return "redirect:/logout";
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
