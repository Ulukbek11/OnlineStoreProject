package com.example.OnlineStoreProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException  {

    public NotFoundException(String entityName, Object id) {
        super(entityName + " with id " + id + " not found");
    }

    public NotFoundException(String entityName) {
        super(entityName + " not found");
    }

}
