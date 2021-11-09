package com.marketplace.backend.controller;

import com.marketplace.backend.mapper.UserMapper;
import com.marketplace.backend.model.Products;
import com.marketplace.backend.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "Rating Controller")
@RestController
@RequestMapping("/product")
@Validated
public class RatingController {
    @Autowired
    LikeService likeService;
    @Autowired
    UserMapper userMapper;


    @ApiOperation(value = "Like Product", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/like", method = RequestMethod.PATCH)
    public Products likeProduct(@RequestParam Long id) {

        return likeService.likeProducts(id);
    }

    @ApiOperation(value = "Dislike Product", authorizations = {@Authorization(value = "Bearer")})
    @RequestMapping(value = "/dislike", method = RequestMethod.PATCH)

    public Products dislikeProduct(@RequestParam Long id) {

        return likeService.dislikeProduct(id);
    }
}
