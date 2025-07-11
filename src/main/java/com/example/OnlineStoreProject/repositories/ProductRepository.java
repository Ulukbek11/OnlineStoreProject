package com.example.OnlineStoreProject.repositories;

import com.example.OnlineStoreProject.models.enums.Category;
import com.example.OnlineStoreProject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategory(Category category);
}
