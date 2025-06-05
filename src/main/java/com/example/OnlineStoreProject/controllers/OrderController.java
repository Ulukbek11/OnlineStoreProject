package com.example.OnlineStoreProject.controllers;

import com.example.OnlineStoreProject.models.Order;
import com.example.OnlineStoreProject.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/user/{id}/orders")
    public ResponseEntity<List<Order>> getAll(@PathVariable Long id){
        return orderService.getUserOrders(id);
    }

    @PostMapping("/user/{id}/orders")
    public ResponseEntity<Order> save(@PathVariable Long id, @RequestBody Order order){
        return orderService.save(id, order);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<String> delete(@PathVariable Long orderId){
        return orderService.delete(orderId);
    }

    @DeleteMapping("/orders/items/{itemId}")
    public ResponseEntity<Order> deleteItem(@PathVariable Long itemId){
        return orderService.deleteItem(itemId);
    }
}
