package com.example.OnlineStoreProject.models;

import lombok.Getter;

@Getter
public class UpdateProductCommand {

    private Long id;
    private Product product;

    public UpdateProductCommand(Long id, Product product) {
        this.id = id;
        this.product = product;
    }
}
