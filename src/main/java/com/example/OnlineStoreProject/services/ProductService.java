package com.example.OnlineStoreProject.services;

import com.example.OnlineStoreProject.exceptions.NotFoundException;
import com.example.OnlineStoreProject.exceptions.ProductNotFoundException;
import com.example.OnlineStoreProject.models.enums.Category;
import com.example.OnlineStoreProject.models.Product;
import com.example.OnlineStoreProject.repositories.ProductRepository;
import com.example.OnlineStoreProject.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok( productRepository.findAll());
    }

    public ResponseEntity<Product> get(Long id) {
        if (productRepository.existsById(id)) {
            return ResponseEntity.ok(productRepository.findById(id).get());
        }
        throw new NotFoundException("Product", id);
    }

    public ResponseEntity<List<Product>> getByCategory(String category) {
        return ResponseEntity.ok(productRepository.findByCategory(Category.valueOf(category)));
    }

    public ResponseEntity<List<Product>> searchByName(String name) {
        return ResponseEntity.ok(productRepository.findByNameContainingIgnoreCase(name));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> add(Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Product added successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Product deleted successfully");
        }
        throw new NotFoundException("Product", id);    }

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> update(Long id, Product newProduct) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product", id));

            if (newProduct.getName() != null) {product.setName(newProduct.getName());}

            if (newProduct.getDescription() != null) {product.setDescription(newProduct.getDescription());}

            if (newProduct.getPrice() != null) {product.setPrice(newProduct.getPrice());}

            ProductValidator.validate(product);

            productRepository.save(product);
            return ResponseEntity.ok(product);

    }
}
