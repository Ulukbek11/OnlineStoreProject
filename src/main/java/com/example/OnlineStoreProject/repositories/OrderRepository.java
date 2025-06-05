package com.example.OnlineStoreProject.repositories;

import com.example.OnlineStoreProject.models.Order;
import com.example.OnlineStoreProject.models.OrderItem;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<List<Order>> findByUserId(Long userId);

    Order findByOrderItems(List<OrderItem> orderItems);

    @Modifying
    @Transactional
    @Query("DELETE FROM Order o WHERE o.id = :orderId")
    void deleteOrderById(@Param("orderId") Long orderId);

}
