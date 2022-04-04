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



			productService.addProduct(new Product(null,"Product 1","Classic Peace Lily is a spathiphyllum floor plant arranged in a bamboo planter with a blue & red ribbom and butterfly pick.",null, BigDecimal.valueOf(12.3),10,new Category("1")));
			productService.addProduct(new Product(null,"Product 2","Classic Peace Lily is a spathiphyllum floor plant arranged in a bamboo planter with a blue & red ribbom and butterfly pick.",null, BigDecimal.valueOf(12.3),10,new Category("2")));
			productService.addProduct(new Product(null,"Product 3","Classic Peace Lily is a spathiphyllum floor plant arranged in a bamboo planter with a blue & red ribbom and butterfly pick.",null, BigDecimal.valueOf(12.3),10,new Category("3")));
			productService.addProduct(new Product(null,"Product 4","Classic Peace Lily is a spathiphyllum floor plant arranged in a bamboo planter with a blue & red ribbom and butterfly pick.",null, BigDecimal.valueOf(12.3),10,new Category("4")));
			productService.addProduct(new Product(null,"Product 5","Classic Peace Lily is a spathiphyllum floor plant arranged in a bamboo planter with a blue & red ribbom and butterfly pick.",null, BigDecimal.valueOf(12.3),10,new Category("5")));
			productService.addProduct(new Product(null,"Product 6","Classic Peace Lily is a spathiphyllum floor plant arranged in a bamboo planter with a blue & red ribbom and butterfly pick.",null, BigDecimal.valueOf(12.3),10,new Category("6")));
			productService.addProduct(new Product(null,"Product 7","Classic Peace Lily is a spathiphyllum floor plant arranged in a bamboo planter with a blue & red ribbom and butterfly pick.",null, BigDecimal.valueOf(12.3),10,new Category("7")));
			productService.addProduct(new Product(null,"Product 8","Classic Peace Lily is a spathiphyllum floor plant arranged in a bamboo planter with a blue & red ribbom and butterfly pick.",null, BigDecimal.valueOf(12.3),10,new Category("8")));
			productService.addProduct(new Product(null,"Product 9","Classic Peace Lily is a spathiphyllum floor plant arranged in a bamboo planter with a blue & red ribbom and butterfly pick.",null, BigDecimal.valueOf(12.3),10,new Category("9")));


		};
	}
}