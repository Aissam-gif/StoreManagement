package net.codejava.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/products")
    public String showProducts(){
        return "admin_products";
    }

}
