package net.codejava.service;

import lombok.extern.slf4j.Slf4j;
import net.codejava.config.CustomUserDetails;
import net.codejava.model.User;
import net.codejava.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if (user == null ){
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		} else {
			log.info("User found in the database: {} ", username);
		}
		return new CustomUserDetails(user);
	}

}
