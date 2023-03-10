package co.id.bvk.springbootshoppingcart.controller;

import co.id.bvk.springbootshoppingcart.entity.Cart;
import co.id.bvk.springbootshoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/api/carts")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping()
    public ResponseEntity<Object> getCart(@RequestHeader(value = "cartId") Long cartId){
        Cart cart = cartService.getCart(cartId);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping(params = {"itemId", "qty"})
    public ResponseEntity<Cart> addItemToCart(@RequestParam("itemId") Long itemId, @RequestParam("qty")  Integer qty, @RequestHeader(value = "cartId") Long cartId) {
        cartService.addItemToCart(cartId, itemId, qty);
        Cart cart = cartService.getCart(cartId);
        return  new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping(params = {"itemId"})
    public ResponseEntity<Cart> removeItemFromCart(@RequestParam("itemId") Long itemId, @RequestHeader(value = "cartId") Long cartId){
        Cart cart = cartService.getCart(cartId);
        if(cart != null){
            cartService.deleteItemFromCart(cart, itemId);
        }
        return  new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping("/totalAmount")
    public ResponseEntity<Object> getTotalAmountCart(@RequestHeader(value = "cartId") Long cartId) {
        Cart cart = cartService.getCart(cartId);
        BigDecimal totalAmountCart = cartService.getTotalAmountCart(cart);
        Map<String, BigDecimal> resultMap = new HashMap<>();
        resultMap.put("totalAmount", totalAmountCart);

        return new ResponseEntity<>(totalAmountCart, HttpStatus.OK);
    }
}
