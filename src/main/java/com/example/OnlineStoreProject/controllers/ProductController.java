package com.example.OnlineStoreProject.controllers;

import com.example.OnlineStoreProject.models.Product;
import com.example.OnlineStoreProject.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/getByName")
    public ResponseEntity<List<Product>> getByName(@RequestParam String name) {
        return productService.searchByName(name);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Product>> getByCategory(@RequestParam String category) {
        return productService.getByCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return productService.get(id);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Valid Product product) {
        return productService.add(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return productService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                @RequestBody @Valid Product product) {
        return productService.update(id, product);
    }
}
