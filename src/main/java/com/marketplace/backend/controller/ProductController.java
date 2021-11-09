package com.marketplace.backend.controller;

import com.marketplace.backend.model.AllProducts;
import com.marketplace.backend.model.ProductRequest;
import com.marketplace.backend.model.Products;
import com.marketplace.backend.service.LikeService;
import com.marketplace.backend.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@Api(tags = "Product Controller")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    LikeService likeService;
    @Autowired
    private ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create product", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping
    public Products create(@Valid @RequestBody ProductRequest productRequest) {
        return productService.create(productRequest);
    }

    @ApiOperation(value = "Update product", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Products update(@Valid @RequestBody ProductRequest product, @RequestParam Long id) {
        return productService.update(product, id);
    }

    @ApiOperation(value = "Delete product", authorizations = {@Authorization(value = "Bearer")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Products delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @ApiOperation(value = "Product by id", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Products product(@PathVariable Long id) {
        return productService.ShowId(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AllProducts> getAllProducts(@RequestParam(value = "page-number", defaultValue = "1") @Min(0) int pageNumber,
                                            @RequestParam(value = "page-size", defaultValue = "2") @Min(1) int pageSize) {
        return productService.AllProducts(pageNumber, pageSize);
    }
}
