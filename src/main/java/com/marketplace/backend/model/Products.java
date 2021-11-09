package com.marketplace.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    private Long id;
    private String name;
    private String description;
    private double price;
    @JsonProperty(value = "user_id")
    private Long userId;
    private Long likes;
    private Long dislikes;
    private List<Comment> comments;

    public Products(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
