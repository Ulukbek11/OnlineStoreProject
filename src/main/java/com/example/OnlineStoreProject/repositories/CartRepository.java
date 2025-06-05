package com.example.OnlineStoreProject.repositories;

import com.example.OnlineStoreProject.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    public Optional<Cart> findByUserId(Long userId);
}
