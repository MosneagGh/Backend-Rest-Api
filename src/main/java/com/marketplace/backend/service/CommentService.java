package com.marketplace.backend.service;

import com.marketplace.backend.exception.CommentException;
import com.marketplace.backend.exception.NotFoundException;
import com.marketplace.backend.mapper.CommentMapper;
import com.marketplace.backend.mapper.NotificationMapper;
import com.marketplace.backend.mapper.ProductMapper;
import com.marketplace.backend.mapper.UserMapper;
import com.marketplace.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
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

    public Comment addComment(Long id, String comm) {
        Products product = productMapper.findById(id);
        User user = getCurrentUserId();
        CommentJoin commentJoin = commentMapper.join(id);
        Comment comment = new Comment(product.getId(), product.getId(), user.getId(), comm);
        Notification notification = new Notification(user.getId(), product.getUserId(), "New comment", current);
        if (comment.getMessage() == null) {
            throw new RuntimeException("Fild comment is required");
        }
        commentMapper.save(comment);
        notificationMapper.save(notification);

        if (notification.getUserId().equals(notification.getToUser())) {
            notificationMapper.delete();
        }
        return comment;
    }

    public List<Comment> showComment(Long id) {
        List<Comment> comments = commentMapper.allComments(id);
        return comments;
    }

    public Comment deleteComment(Long id) {
        Comment comment = commentMapper.findById(id);
        CommentJoin comments = commentMapper.join(id);
        if (comment == null) throw new NotFoundException("Comment not found");
        if (getCurrentUserId().getId().equals(comments.getUserId()) ||
                getCurrentUserId().getId().equals(comment.getUserId())) {
            commentMapper.deleteById(id);
        } else throw new CommentException("Not posible to delete");

        return comment;
    }

    public Comment updateComment(CommentRequest message, Long id) {
        Comment comment = commentMapper.findById(id);
        CommentJoin comments = commentMapper.join(id);
        if (comment == null) throw new NotFoundException("Comment not found");
        if (getCurrentUserId().getId().equals(comment.getUserId()) ||
                getCurrentUserId().getId().equals(comments.getUserId())) {
            comment.setMessage(message.getMessage());
            commentMapper.update(comment);
        } else throw new CommentException("Not posible to change");

        return comment;
    }
}
