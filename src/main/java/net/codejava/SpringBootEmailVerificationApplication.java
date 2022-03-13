package net.codejava;

import net.codejava.model.Role;
import net.codejava.model.User;
import net.codejava.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SpringBootEmailVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmailVerificationApplication.class, args);
	}
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));

			userService.registerUser(new User(null,"said@user.com","karimo","karim","karim",null,true, new ArrayList<>()),null);
			userService.registerUser(new User(null,"said@admin.com","karimo","karim","karim",null,true, new ArrayList<>()),null);
			userService.registerUser(new User(null,"said@manager.com","karimo","karim","karim",null,true, new ArrayList<>()),null);


			userService.addRoleToUser("said@user.com","ROLE_USER");
			userService.addRoleToUser("said@manager.com","ROLE_MANAGER");
			userService.addRoleToUser("said@admin.com","ROLE_ADMIN");
		};
	}
}
