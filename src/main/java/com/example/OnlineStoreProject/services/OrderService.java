package com.example.OnlineStoreProject.services;

import com.example.OnlineStoreProject.exceptions.NotFoundException;
import com.example.OnlineStoreProject.models.*;
import com.example.OnlineStoreProject.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public ResponseEntity<Order> save(Long id, Order order) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", id));
        Cart cart = user.getCart();
        List<CartItem> cartItems = user.getCart().getCartItems();

                if(user.getAddress().getCountry() == null){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                order.setUser(user);
                order.setAddress(user.getAddress());
                order.setOrderItems(Order.cartToOrder(order, cartItems));
                order.setQuantity(order.getQuantity());
                order.setTotalPrice(order.getTotalPrice());

                cartItemRepository.deleteAll(cartItems);
                cart.getCartItems().clear();
                Cart.updateCartTotals(cart);
                cartRepository.save(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderRepository.save(order));
    }

    public ResponseEntity<List<Order>> getUserOrders(Long id) {
        return ResponseEntity.ok(orderRepository.findByUserId(id).orElseThrow(() -> new NotFoundException("User", id)));
    }

    @Transactional
    public ResponseEntity<String> delete(Long orderId) {
        Order order = orderRepository.findById(orderId)
                  .orElseThrow(() -> new NotFoundException("Order", orderId));

        order.getOrderItems().forEach(orderItem -> {orderItem.setOrder(null);});
        orderRepository.deleteOrderById(orderId);

        return ResponseEntity.ok("Order deleted successfully");
    }

    public ResponseEntity<Order> deleteItem(Long itemId) {
        OrderItem orderItem = orderItemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("OrderItem", itemId));

        orderItemRepository.deleteById(itemId);
        return ResponseEntity.ok(orderRepository.findByOrderItems(List.of(orderItem)));
    }

}
