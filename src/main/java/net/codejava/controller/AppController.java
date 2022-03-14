package net.codejava.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import net.codejava.model.User;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

	@GetMapping("")
	public String viewIndexPage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/home";
		}
		return "index";
	}

	@GetMapping("/home")
	public String viewHomePage() {
		return "home";
	}

	@GetMapping("/contact")
	public String viewContactPage() {
		return "contact";
	}

	@GetMapping("/about")
	public String viewAboutPage() {
		return "about";
	}

}
