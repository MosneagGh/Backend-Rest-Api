package com.marketplace.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private Long id;
    private Long userId;
    private Long toUser;
    private String messages;
    private LocalDateTime createdAt;

    public Notification(Long userId, Long toUser, String message, LocalDateTime createdAt) {
        this.userId = userId;
        this.toUser = toUser;
        this.messages = message;
        this.createdAt = createdAt;
    }
}
