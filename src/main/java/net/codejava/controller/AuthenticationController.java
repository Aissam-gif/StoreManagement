package net.codejava.controller;


import net.codejava.model.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AuthenticationController {

    @GetMapping("/login")
    public String showLoginForm() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/home";
        }

        return "login_form";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/home";
        }
        model.addAttribute("user", new User());
        return "signup_form";
    }

}
