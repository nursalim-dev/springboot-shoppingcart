package co.id.bvk.springbootshoppingcart.service;

import co.id.bvk.springbootshoppingcart.entity.Cart;
import co.id.bvk.springbootshoppingcart.entity.CartItem;
import co.id.bvk.springbootshoppingcart.entity.Item;
import co.id.bvk.springbootshoppingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CartRepository cartRepository;

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId).get();
    }

    public void addItemToCart(Long cartId, Long itemId, long quantity){

        Item item = itemService.findById(itemId);
        CartItem cartItem = new CartItem();

        Cart cart = getCart(cartId);

        if(cart == null){
            cart = new Cart();

            cartItem.setCart(cart);
            cartItem.setItem(item);
            cartItem.setQty(quantity);
            BigDecimal subtotal = item.getPrice().multiply(new BigDecimal(Long.valueOf(quantity)));
            cartItem.setSubTotal(subtotal);

            // Add Item to cart

            List cartItemList = new ArrayList();
            cartItemList.add(cartItem);
            cart.setCartItems(cartItemList);
            cartRepository.save(cart);

        } else {
            cartItem.setCart(cart);
            cartItem.setItem(item);
            cartItem.setQty(quantity);
            BigDecimal subtotal = item.getPrice().multiply(new BigDecimal(Long.valueOf(quantity)));
            cartItem.setSubTotal(subtotal);

            // Add Item to cart
            List cartItemList = new ArrayList();
            cartItemList.add(cartItem);
            cart.setCartItems(cartItemList);
            cartRepository.save(cart);

            cartRepository.save(cart);
        }

    }

    public void deleteItemFromCart(Cart cart, Long itemId) {
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.stream().filter(cartItem -> !itemId.equals(cartItem.getItem().getId())).collect(Collectors.toList());
    }
}
