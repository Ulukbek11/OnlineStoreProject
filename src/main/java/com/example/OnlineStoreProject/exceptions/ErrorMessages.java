package com.example.OnlineStoreProject.exceptions;

public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product not found"),
    PRODUCT_IS_NOT_VALID("Product is not valid");

    private final String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
