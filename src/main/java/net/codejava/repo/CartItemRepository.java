package net.codejava.repo;

import net.codejava.model.CartItem;
import net.codejava.model.Product;
import net.codejava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    void deleteByUserAndProduct(User user, Product product);
    CartItem findByUserAndProduct(User user, Product product);
   // void updateQuantity(int quantity,int productId, int userId);
    void deleteCartItemsByUser(User user);
}
