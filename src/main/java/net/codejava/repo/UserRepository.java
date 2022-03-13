package net.codejava.repo;

import net.codejava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findByVerificationCode(String code);
}
