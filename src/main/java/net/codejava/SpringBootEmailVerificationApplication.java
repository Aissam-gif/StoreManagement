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
			userServiceImpl.saveRole(new Role(null,"ROLE_USER",null));
			userServiceImpl.saveRole(new Role(null,"ROLE_ADMIN",null));

			userServiceImpl.registerUser(new User(null,"said@user.com","karimo","karim","karim",null,true,null),null);
			userServiceImpl.registerUser(new User(null,"said@admin.com","karimo","karim","karim",null,true,null),null);

			userServiceImpl.addRoleToUser("said@user.com","ROLE_USER");
			userServiceImpl.addRoleToUser("said@admin.com","ROLE_ADMIN");


			categoryService.saveCategory("Money tree");
			categoryService.saveCategory("Fiddle leaf fig");
			categoryService.saveCategory("Rubber plant");


			productService.addProduct(new Product(null,"Product 1","Produit 1","https://s-media-cache-ak0.pinimg.com/236x/3b/36/ca/3b36ca3afe0fa0fd4984b9eee2e154bb.jpg", BigDecimal.valueOf(12.3),10,null));
			productService.addProduct(new Product(null,"Product 2","Product 2","https://s-media-cache-ak0.pinimg.com/236x/3b/36/ca/3b36ca3afe0fa0fd4984b9eee2e154bb.jpg", BigDecimal.valueOf(12.3),10,null));
			productService.addProduct(new Product(null,"Product 3","Product 3","https://s-media-cache-ak0.pinimg.com/236x/3b/36/ca/3b36ca3afe0fa0fd4984b9eee2e154bb.jpg", BigDecimal.valueOf(12.3),10,null));
			productService.addProduct(new Product(null,"Product 4","Product 4","https://s-media-cache-ak0.pinimg.com/236x/3b/36/ca/3b36ca3afe0fa0fd4984b9eee2e154bb.jpg", BigDecimal.valueOf(12.3),10,null));
			productService.addProduct(new Product(null,"Product 5","Product 5","https://s-media-cache-ak0.pinimg.com/236x/3b/36/ca/3b36ca3afe0fa0fd4984b9eee2e154bb.jpg", BigDecimal.valueOf(12.3),10,null));
			productService.addProduct(new Product(null,"Product 6","Product 6","https://s-media-cache-ak0.pinimg.com/236x/3b/36/ca/3b36ca3afe0fa0fd4984b9eee2e154bb.jpg", BigDecimal.valueOf(12.3),10,null));
			productService.addProduct(new Product(null,"Product 7","Product 7","https://s-media-cache-ak0.pinimg.com/236x/3b/36/ca/3b36ca3afe0fa0fd4984b9eee2e154bb.jpg", BigDecimal.valueOf(12.3),10,null));


		};
	}
}