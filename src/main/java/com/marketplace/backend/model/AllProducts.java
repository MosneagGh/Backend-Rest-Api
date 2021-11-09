package com.marketplace.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class AllProducts {
    private Long id;
    private String name;
    private String description;
    private double price;
    @JsonProperty(value = "user_id")
    private Long userId;
}
