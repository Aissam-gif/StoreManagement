package net.codejava.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    @GetMapping("/home")
    public String viewHomePage() {
        return "home";
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
