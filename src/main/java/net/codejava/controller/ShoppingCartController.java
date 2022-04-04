package net.codejava.controller;

import lombok.extern.slf4j.Slf4j;
import net.codejava.config.CustomUserDetails;
import net.codejava.model.CartItem;
import net.codejava.model.User;
import net.codejava.service.CartItemService;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller @Slf4j
public class ShoppingCartController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/cart")
    public String showCart(Model model,
                           @AuthenticationPrincipal CustomUserDetails customUserDetails) {
         User user = customUserDetails.getUser();
         List<CartItem> cartItems = cartItemService.listCartItems(user);
        model.addAttribute("cartItems", cartItems);
        log.info("Number of cart Items : " + cartItems.size());
        return "shopping_cart";
    }

    @GetMapping("/cart/add/{pid}/{qty}")
    public String addProductToCart(@PathVariable("pid") Long productId,
                                   @PathVariable("qty") int quantity,
                                   @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                   RedirectAttributes ra) {
        User user = customUserDetails.getUser();
        Integer addedQuantity = cartItemService.addProduct(quantity,productId,user);
        log.info(addedQuantity + " item(s) of this product were added to your shopping cart.");
        ra.addFlashAttribute("message","Item added to Cart");
        return "redirect:/home";
    }

    @GetMapping("/cart/remove/{pid}")
    public String removeProductFromCart(@PathVariable("pid") Long productId,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        RedirectAttributes ra)
    {
        User user = customUserDetails.getUser();
        cartItemService.removeProduct(productId, user);
        ra.addFlashAttribute("message", "Product removed from cart");
        return "shopping_cart";
    }
}
