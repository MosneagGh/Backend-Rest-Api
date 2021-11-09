package com.marketplace.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private Long id;
    private Long productId;
    private Long userId;
    private String message;

    public Comment(Long productId, Long userId, String message) {
        this.productId = productId;
        this.userId = userId;
        this.message = message;
    }

    public Comment(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }
}
