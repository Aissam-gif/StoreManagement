package net.codejava.controller;

import lombok.extern.slf4j.Slf4j;
import net.codejava.email.IEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;

@Controller @Slf4j
public class MainController {
	@Autowired
	private IEmailSender emailSender;

	@Autowired
	private LocaleResolver localeResolver;

	@GetMapping("")
	public String viewIndexPage(Model model, HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(localeResolver.resolveLocale(request).getLanguage().equals("en")){
			model.addAttribute("lang","Francais");
		}else{
			model.addAttribute("lang","English");
		}
		if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/home";
		}
		return "index";
	}

	@GetMapping("/lang")
	public String changeLanguage(HttpServletRequest request){
		if(localeResolver.resolveLocale(request).getLanguage().equals("en")){
			return "redirect:/?lang=fr";
		}
		return "redirect:/?lang=en";
	}

	@GetMapping("/contact")
	public String viewContactPage() {
		return "contact";
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
