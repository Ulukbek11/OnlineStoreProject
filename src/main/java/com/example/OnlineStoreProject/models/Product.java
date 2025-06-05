package com.example.OnlineStoreProject.models;

import com.example.OnlineStoreProject.models.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private Long id;

    @Size(min = 2, max = 50, message = "Name is required")
    @NotNull
    private String name;

    @Size(max = 1000, message = "description is required")
    @NotNull
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Category category;

    @DecimalMin(value = "0.01", message = "Price cannot be 0 or less")
    @NotNull
    private Double price;

}
