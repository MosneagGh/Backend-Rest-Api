package com.marketplace.backend.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {
    private String name;
    private String description;
    @NotNull(message = "Price by product not be 0 or negative number")
    private double price;
}
