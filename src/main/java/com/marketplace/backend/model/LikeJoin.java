package com.marketplace.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeJoin {
    Long id;
    Long productId;
    Long userId;

    public LikeJoin(Long productId, Long userId) {
        this.productId = productId;
        this.userId = userId;
    }
}
