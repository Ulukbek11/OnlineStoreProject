package com.example.OnlineStoreProject.services;

import com.example.OnlineStoreProject.exceptions.NotFoundException;
import com.example.OnlineStoreProject.models.Cart;
import com.example.OnlineStoreProject.models.CartItem;
import com.example.OnlineStoreProject.models.Product;
import com.example.OnlineStoreProject.repositories.CartItemRepository;
import com.example.OnlineStoreProject.repositories.CartRepository;
import com.example.OnlineStoreProject.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<Cart> getByUserId(Long id) {
        return ResponseEntity.ok(cartRepository.findByUserId(id).orElseThrow());
    }

    public ResponseEntity<Cart> addItem(Long id, CartItem cartItem) {
        Cart cart = cartRepository.findByUserId(id).orElseThrow(() -> new NotFoundException("User", id));
        Product product = productRepository.findById(cartItem.getProduct().getId()).orElseThrow(() -> new NotFoundException("Product", id));
        cartItem.setProduct(product);

        //Если Product уже есть в cart то просто меняем его quantity
        for (CartItem item : cart.getCartItems()) {
            if(product.getId().equals(item.getProduct().getId())) {
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                item.setTotalPrice(product.getPrice() * item.getQuantity());

                Cart.updateCartTotals(cart);


                cartItemRepository.save(item);
                cartRepository.save(cart);
                return ResponseEntity.status(HttpStatus.CREATED).body(cart);
            }
        }


        cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
        cartItem.setCart(cart);

        cart.getCartItems().add(cartItem);
        Cart.updateCartTotals(cart);

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    public ResponseEntity<Cart> removeItem(Long id, Long itemId) {
        cartItemRepository.deleteById(itemId);
        return ResponseEntity.ok(cartRepository.findByUserId(id).orElseThrow(() -> new NotFoundException("User", id)));
    }

    public ResponseEntity<Cart> updateItem(Long id, Long itemId, Integer quantity) {
        if (cartItemRepository.existsById(itemId) && quantity > 0) {
            CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("CartItem", id));
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
            return ResponseEntity.ok(cartRepository.save(cartRepository.findByUserId(id).orElseThrow(() -> new NotFoundException("User", id))));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
