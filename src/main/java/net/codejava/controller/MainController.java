package net.codejava.controller;

import lombok.extern.slf4j.Slf4j;
import net.codejava.email.IEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller @Slf4j
public class MainController {
	@Autowired
	private IEmailSender emailSender;

	@GetMapping("")
	public String viewIndexPage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/home";
		}
		return "index";
	}

	@GetMapping("/contact")
	public String viewContactPage() {
		return "contact";
	}

	@GetMapping("/about")
	public String viewAboutPage() {
		return "about";
	}

	@PostMapping("/sendmail")
	public String sendContactMail(@RequestParam("name") String name,
								  @RequestParam("email") String email,
								  @RequestParam("message") String message)
	{
		if (emailSender.getEmail(name, email, message))
		{
			return "redirect:/contact?success=1";
		} else {
			return "redirect:/contact?success=0";
		}

	}
}
