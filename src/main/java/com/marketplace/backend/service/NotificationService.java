package com.marketplace.backend.service;

import com.marketplace.backend.exception.NotFoundException;
import com.marketplace.backend.mapper.CommentMapper;
import com.marketplace.backend.mapper.NotificationMapper;
import com.marketplace.backend.mapper.ProductMapper;
import com.marketplace.backend.mapper.UserMapper;
import com.marketplace.backend.model.Notification;
import com.marketplace.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    public com.marketplace.backend.model.User getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userMapper.findByUsername(authentication.getName());
    }

    public List<Notification> allNotif() {
        User user = getCurrentUserId();
        List<Notification> notification = notificationMapper.allNotification(user.getId());
        if (notification.size() == 0) throw new NotFoundException("Notification not found");
        return notification;
    }

    public Notification byId(Long id) {
        User user = getCurrentUserId();
        Notification notification = notificationMapper.findByIdAndUser(user.getId(), id);
        if (notification == null) throw new NotFoundException("Notification not found");
        return notification;
    }
}
