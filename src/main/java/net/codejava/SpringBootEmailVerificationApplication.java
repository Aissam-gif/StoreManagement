package net.codejava;

import net.codejava.model.Category;
import net.codejava.model.Product;
import net.codejava.model.Role;
import net.codejava.model.User;
import net.codejava.service.CategoryService;
import net.codejava.service.ProductService;
import net.codejava.service.UserService;
import net.codejava.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootEmailVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmailVerificationApplication.class, args);
	}
	@Bean
	CommandLineRunner run(UserService userServiceImpl, CategoryService categoryService, ProductService productService) {

		return args -> {
			/*
			userServiceImpl.saveRole(new Role(null,"ROLE_USER",null));
			userServiceImpl.saveRole(new Role(null,"ROLE_ADMIN",null));

			userServiceImpl.registerUser(new User(null,"said@admin.com","karimo","karim","karim",null,true,null),null);

			userServiceImpl.addRoleToUser("said@admin.com","ROLE_ADMIN");
*/
		};
	}
}