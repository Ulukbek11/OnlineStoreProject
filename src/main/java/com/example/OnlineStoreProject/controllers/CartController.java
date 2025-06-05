package com.example.OnlineStoreProject.controllers;

import com.example.OnlineStoreProject.models.Cart;
import com.example.OnlineStoreProject.models.CartItem;
import com.example.OnlineStoreProject.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{id}/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        return cartService.getByUserId(id);
    }

    @PostMapping
    public ResponseEntity<Cart> addCartItem(@PathVariable Long id,
                                            @RequestBody CartItem cartItem) {
        return cartService.addItem(id, cartItem);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long id,
                                               @PathVariable Long itemId ) {
        return cartService.removeItem(id, itemId);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable Long id,
                                               @PathVariable Long itemId,
                                               @RequestParam Integer quantity
                                               ) {
        return cartService.updateItem(id, itemId, quantity);
    }
}
