package com.example.OnlineStoreProject.services;

import com.example.OnlineStoreProject.exceptions.ProductNotFoundException;
import com.example.OnlineStoreProject.models.Product;
import com.example.OnlineStoreProject.models.UpdateProductCommand;
import com.example.OnlineStoreProject.repositories.ProductRepository;
import com.example.OnlineStoreProject.validators.ProductValidator;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    public ResponseEntity<Product> get(Long id) {
        if (productRepository.existsById(id)) {
            return ResponseEntity.ok(productRepository.findById(id).get());
        }
        throw new ProductNotFoundException();
    }

    public ResponseEntity<List<Product>> searchByName(String name) {
        return ResponseEntity.ok(productRepository.findByNameContainingIgnoreCase(name));
    }

    public ResponseEntity<String> add(Product product) {
//        ProductValidator.validate(product);
        productRepository.save(product);
        return ResponseEntity.ok("Product added successfully");
    }

    public ResponseEntity<String> delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Product deleted successfully");
        }
        throw new ProductNotFoundException();
    }

    public ResponseEntity<Product> update(Long id, Product newProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            if (newProduct.getName() != null) {product.setName(newProduct.getName());}

            if (newProduct.getDescription() != null) {product.setDescription(newProduct.getDescription());}

            if (newProduct.getPrice() != null) {product.setPrice(newProduct.getPrice());}

            ProductValidator.validate(product);

            productRepository.save(product);
            return ResponseEntity.ok(product);
        }
        throw new ProductNotFoundException();
    }
}
