package com.marketplace.backend.controller;


import com.marketplace.backend.model.Notification;
import com.marketplace.backend.service.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Notification Controller")
@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @ApiOperation(value = "Show notification", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping
    public List<Notification> show() {
        return notificationService.allNotif();
    }

    @ApiOperation(value = "Notification by id", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/{id}")
    public Notification byId(@PathVariable Long id) {
        return notificationService.byId(id);
    }
}
