package com.example.OnlineStoreProject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CartItem> cartItems;

    private Integer quantity;

    private Double totalPrice;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

//    public Double calcTotalPrice() {
//        if (cartItems == null) { return 0.0;}
//        double totalPrice = 0;
//        for (CartItem cartItem : cartItems) {
//            totalPrice += cartItem.getTotalPrice();
//        }
//        return totalPrice;
//    }
//
//    public Integer calcQuantity() {
//        if (cartItems == null) { return 0;}
//        int quantity = 0;
//        for (CartItem cartItems : cartItems) {
//            quantity += cartItems.getQuantity();
//        }
//        return quantity;
//    }

    public static void updateCartTotals(Cart cart) {
        cart.setQuantity(cart.getCartItems().stream()
                .map(CartItem::getQuantity)
                .reduce(0, Integer::sum));

        cart.setTotalPrice(cart.getCartItems().stream()
                .map(CartItem::getTotalPrice)
                .reduce(0.0, Double::sum));
    }

}
