package net.codejava.repo;

import net.codejava.model.Category;
import net.codejava.model.Role;
import net.codejava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findByVerificationCode(String code);
	List<User> findUsersByRole(Role role);
}
