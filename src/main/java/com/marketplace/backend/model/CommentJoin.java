package com.marketplace.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentJoin {
    Long id;
    Long productId;
    Long userId;
}
