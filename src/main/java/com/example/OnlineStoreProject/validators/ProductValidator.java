package com.example.OnlineStoreProject.validators;

import com.example.OnlineStoreProject.exceptions.ProductNotValidException;
import com.example.OnlineStoreProject.models.Product;
import org.springframework.validation.annotation.Validated;

@Validated
public class ProductValidator {

    public static void validate(Product product) {
        if (product.getName().length() > 50 || product.getName() == null || product.getName().isEmpty()) {
            throw new ProductNotValidException();
        }
        if (product.getDescription().length() > 1000 || product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new ProductNotValidException();
        }
        if (product.getPrice() <= 0 || product.getPrice() == null) {
            throw new ProductNotValidException();
        }
    }
}
