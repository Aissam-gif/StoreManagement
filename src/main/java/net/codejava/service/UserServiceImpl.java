package net.codejava.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.codejava.model.Role;
import net.codejava.model.User;
import net.codejava.repo.RoleRepository;
import net.codejava.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public List<User> listAll() {
		return repo.findAll();
	}
	
	@Override
	public void register(User user, String siteURL)
			throws UnsupportedEncodingException, MessagingException {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		String randomCode = RandomString.make(64);
		user.setVerificationCode(randomCode);
		user.setEnabled(false);
		if (sendVerificationEmail(user, siteURL)) {
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


	private boolean sendVerificationEmail(User user, String siteURL)
			throws UnsupportedEncodingException {
		String toAddress = user.getEmail();
		String fromAddress = "abdelkarim.elbouroumi@usmba.ac.ma";
		String senderName = "Karim Official";
		String subject = "Please verify your registration";
		String content = "Dear [[name]],<br>"
				+ "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
				+ "Thank you,<br>"
				+ "Your company name.";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom(fromAddress, senderName);
			helper.setTo(toAddress);
			helper.setSubject(subject);

			content = content.replace("[[name]]", user.getFullName());
			String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

			content = content.replace("[[URL]]", verifyURL);

			helper.setText(content, true);
			mailSender.send(message);
			log.info("email sent Success");
		} catch (MessagingException | MailException e) {
			return false;
		}

		return true;

	}
	
	public boolean verify(String verificationCode) {
		User user = repo.findByVerificationCode(verificationCode);
		
		if (user == null || user.isEnabled()) {
			return false;
		} else {
			user.setVerificationCode(null);
			user.setEnabled(true);
			repo.save(user);
			
			return true;
		}
		
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
}
