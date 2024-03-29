package net.codejava.service;

import java.util.List;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.codejava.email.IEmailSender;
import net.codejava.model.Role;
import net.codejava.model.User;
import net.codejava.repo.CartItemRepository;
import net.codejava.repo.ProductRepository;
import net.codejava.repo.RoleRepository;
import net.codejava.repo.UserRepository;
import net.codejava.token.ITokenVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

@Service @Slf4j @Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IEmailSender emailSender;

	@Autowired
	private ITokenVerification tokenVerification;

	@Override
	public List<User> getUsers() {
		return repo.findAll();
	}
	
	@Override
	public void register(User user, String siteURL) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		String randomCode = RandomString.make(64);
		user.setVerificationCode(randomCode);
		user.setEnabled(false);
		if (emailSender.sendEmail(user, siteURL)) {
			repo.save(user);
			addRoleToUser(user.getEmail(),"ROLE_USER");
		}
	}

	@Override
	public void registerUser(User user, String siteURL){
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
	}

	@Override
	public boolean verify(String verificationCode) {
		User user = repo.findByVerificationCode(verificationCode);

		if(tokenVerification.verifyUser(user, verificationCode)){
			user.setVerificationCode(null);
			user.setEnabled(true);
			repo.save(user);
			return true;
		}
		return false;
	}

	@Override
	public void saveRole(Role role_user) {
		roleRepository.save(role_user);
	}

	@Override
	public void addRoleToUser(String email, String roleName){
		User user = repo.findByEmail(email);
		Role role = roleRepository.findByName(roleName);
		log.info("Adding role {} to user {} database", role.getName(), user.getEmail());
		user.setRole(role);
	}

	@Override
	public void saveUser(User user) {
		repo.save(user);
	}

	@Override
	public void deleteUser(User user) {
		cartItemRepository.deleteCartItemsByUser(user);
		repo.delete(user);
	}
}
