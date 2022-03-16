package net.codejava.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

	@GetMapping("/403")
	public String viewDeniedPage() {
		return "redirect:/home";
	}

}
