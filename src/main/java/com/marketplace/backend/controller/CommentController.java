package com.marketplace.backend.controller;

import com.marketplace.backend.exception.NotFoundException;
import com.marketplace.backend.mapper.ProductMapper;
import com.marketplace.backend.model.Comment;
import com.marketplace.backend.model.CommentRequest;
import com.marketplace.backend.model.Products;
import com.marketplace.backend.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Comment Controller")
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ProductMapper productMapper;

    @ApiOperation(value = "Add comment", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping
    public Comment comment(@RequestParam Long Product_id, String Comment) {
        Products products = productMapper.findById(Product_id);
        if (products == null) {
            throw new NotFoundException("Product not found");
        }
        return commentService.addComment(Product_id, Comment);
    }

    @ApiOperation(value = "Delete", authorizations = {@Authorization(value = "Bearer")})
    @DeleteMapping("/{id}")
    public Comment deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

    @ApiOperation(value = "Update", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping("/{id}")
    public Comment updateComment(@RequestBody CommentRequest comment, @PathVariable("id") Long id) {
        return commentService.updateComment(comment, id);
    }

}
