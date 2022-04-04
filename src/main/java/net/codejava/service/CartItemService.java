package net.codejava.service;

import net.codejava.model.CartItem;
import net.codejava.model.User;

import java.util.List;

public interface CartItemService {
    Integer addProduct(int quantity, Long productId, User user);
    void removeProduct(Long procutId, User user);
    void updateQuatity(int quantity, Long productId, Long userId);
    List<CartItem> listCartItems(User user);
    Long getItemsCount();
}
