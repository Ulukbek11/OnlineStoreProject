package com.example.OnlineStoreProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotValidException extends RuntimeException {

    public ProductNotValidException() {
        super(ErrorMessages.PRODUCT_IS_NOT_VALID.getErrorMessage());
    }
}
