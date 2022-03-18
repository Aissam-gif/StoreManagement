package net.codejava.repo;

import net.codejava.model.Cart;
import net.codejava.model.CartKey;
import net.codejava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> getCartsByUser(User user);
}
