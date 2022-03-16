package net.codejava;

import net.codejava.model.Role;
import net.codejava.model.User;
import net.codejava.service.UserService;
import net.codejava.service.UserServiceImpl;
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
	CommandLineRunner run(UserService userServiceImpl) {
		return args -> {
			userServiceImpl.saveRole(new Role(null,"ROLE_USER",null));
			userServiceImpl.saveRole(new Role(null,"ROLE_MANAGER",null));
			userServiceImpl.saveRole(new Role(null,"ROLE_ADMIN",null));

			userServiceImpl.registerUser(new User(null,"said@user.com","karimo","karim","karim",null,true, null,null),null);
			userServiceImpl.registerUser(new User(null,"said@admin.com","karimo","karim","karim",null,true, null,null),null);
			userServiceImpl.registerUser(new User(null,"said@manager.com","karimo","karim","karim",null,true,null,null),null);

			userServiceImpl.addRoleToUser("said@user.com","ROLE_USER");
			userServiceImpl.addRoleToUser("said@manager.com","ROLE_MANAGER");
			userServiceImpl.addRoleToUser("said@admin.com","ROLE_ADMIN");
		};
	}
}
