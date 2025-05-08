package com.example.OnlineStoreProject.models;

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
    @SequenceGenerator(name = "product_seq_name", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private Long id;

    @NotNull
    @Size(min = 2, max = 50, message = "Name is required")
    private String name;

//    @NotNull
//    private ArrayList<Image> images;

    @NotNull
    @Size(max = 1000, message = "description is required")
    private String description;

    @NotNull
    @DecimalMin(value = "0.01", message = "Price cannot be 0 or less")
    private Double price;
}
