package com.marketplace.backend.service;


import com.marketplace.backend.exception.HandException;
import com.marketplace.backend.exception.NotFoundException;
import com.marketplace.backend.mapper.LikeMapper;
import com.marketplace.backend.mapper.NotificationMapper;
import com.marketplace.backend.mapper.ProductMapper;
import com.marketplace.backend.mapper.UserMapper;
import com.marketplace.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class LikeService {

    @Autowired
    LikeMapper likeMapper;
    LocalDateTime current = LocalDateTime.now();
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    public com.marketplace.backend.model.User getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userMapper.findByUsername(authentication.getName());
    }

    @Transactional
    public Products likeProducts(Long id) {
        Products product = productMapper.findById(id);
        User user = getCurrentUserId();
        if (product == null) throw new NotFoundException("Product not found");

        if (product.getUserId().equals(user.getId())) {
            throw new HandException(" Not possible to like your own product");
        }

        Like like = likeMapper.findByUserIdAndProdId(user.getId(), product.getId());
        if (like != null) {
            if (like.isLiked()) {
                likeMapper.deleteById(like.getId());
            } else {
                likeMapper.likeProduct(product.getId(), user.getId());
            }
        } else {
            likeMapper.save(new Like(true, product.getId(), user.getId()));
        }
        Notification notification = new Notification(user.getId(), product.getUserId(), "Your product was liked", current);
        notificationMapper.save(notification);
        return product;
    }

    @Transactional
    public Products dislikeProduct(Long id) {
        Products product = productMapper.findById(id);
        User user = getCurrentUserId();
        if (product == null) throw new NotFoundException("Product not found");

        if (product.getUserId().equals(user.getId())) {
            throw new HandException("Not possible to unlike your own product!");
        }
        Like like = likeMapper.findByUserIdAndProdId(user.getId(), product.getId());
        if (like != null) {

            if (!like.isLiked()) {
                likeMapper.deleteById(like.getId());
            } else {
                likeMapper.dislikeProduct(product.getId(), user.getId());
            }
        } else {
            likeMapper.save(new Like(false, product.getId(), user.getId()));
        }
        notificationMapper.save(new Notification(user.getId(),product.getUserId(),"Your product was disliked", current));
        return product;
    }
}