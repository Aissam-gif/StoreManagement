package net.codejava.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.codejava.model.CartItem;
import net.codejava.model.Product;
import net.codejava.model.User;
import net.codejava.repo.CartItemRepository;
import net.codejava.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class CartItemServiceImpl implements CartItemService{
    @Autowired
    private final CartItemRepository cartItemRepository;
    @Autowired
    private final ProductRepository productRepository;


    @Override
    public Integer addProduct(int quantity, Long productId, User user) {
        int addedQuantity = quantity;
        Product product = productRepository.findById(productId).get();
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
        if (cartItem != null) {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(addedQuantity);
            cartItem.setProduct(product);
            cartItem.setUser(user);
        }
        cartItemRepository.save(cartItem);
        return addedQuantity;
    }

    @Override
    public void removeProduct(Long productId, User user) {
        Product product = productRepository.findProductById(productId);
        log.error(String.valueOf(product));
        CartItem cartItem = null;
        if (product != null ) {
            cartItem = cartItemRepository.findByUserAndProduct(user, product);
            cartItemRepository.delete(cartItem);
        }
    }

    @Override
    public void updateQuatity(int quantity, Long productId, Long userId) {

    }

    @Override
    public List<CartItem> listCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    @Override
    public Long getItemsCount() {
        return cartItemRepository.count();
    }
}
