package net.codejava.controller;

import lombok.extern.slf4j.Slf4j;
import net.codejava.model.User;
import net.codejava.service.UserService;
import net.codejava.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller @Slf4j
public class VerificationController {
    @Autowired
    private UserService  service;

    @PostMapping("/process_register")
    public String processRegister(User user, HttpServletRequest request, RedirectAttributes ra)
            throws UnsupportedEncodingException, MessagingException {
        service.register(user, getSiteURL(request));
        return "register_status";
    }



    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code, RedirectAttributes ra) {
        if (service.verify(code)) {
            ra.addFlashAttribute("message", "Account activated Successfully !");
            return "redirect:/login";
        } else {
            ra.addFlashAttribute("message", "Sorry, we could not verify account. It maybe already verified, or verification code is incorrect.");
            return "redirect:/register";
        }

    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

}
