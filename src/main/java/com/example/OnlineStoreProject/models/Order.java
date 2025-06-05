package com.example.OnlineStoreProject.models;

import com.example.OnlineStoreProject.models.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Address address;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Integer quantity;

    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public Double getTotalPrice() {
        if (orderItems == null) { return 0.0;}
        double totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    public Integer getQuantity() {
        if (orderItems == null) { return 0;}
        int quantity = 0;
        for (OrderItem orderItem : orderItems) {
            quantity += orderItem.getQuantity();
        }
        return quantity;
    }

    public static List<OrderItem> cartToOrder(Order order, List<CartItem> cartItems) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            orderItems.add(OrderItem
                    .builder()
                            .product(cartItem.getProduct())
                            .order(order)
                            .quantity(cartItem.getQuantity())
                            .totalPrice(cartItem.getTotalPrice())
                    .build());
        }
        return orderItems;

    }

}
