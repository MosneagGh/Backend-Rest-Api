package com.marketplace.backend.service;

import com.marketplace.backend.exception.HandException;
import com.marketplace.backend.exception.NotFoundException;
import com.marketplace.backend.mapper.CommentMapper;
import com.marketplace.backend.mapper.ProductMapper;
import com.marketplace.backend.mapper.UserMapper;
import com.marketplace.backend.model.AllProducts;
import com.marketplace.backend.model.ProductRequest;
import com.marketplace.backend.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AllProductsService allProductsService;

    public com.marketplace.backend.model.User getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userMapper.findByUsername(authentication.getName());
    }

    public Products create(ProductRequest productRequest) {
        Products products = new Products();
        products.setName(productRequest.getName());
        products.setPrice(productRequest.getPrice());
        products.setDescription(productRequest.getDescription());
        products.setUserId(getCurrentUserId().getId());
        products.setLikes(0L);
        products.setDislikes(0L);
        if (productRequest.getPrice() <= 0 || productRequest.getName() == null)
            throw new HandException("Price don't be null or 0");
        productMapper.save(products);

        return products;
    }

    public Products update(ProductRequest product, Long id) {
        Products products = productMapper.findById(id);
        if (getCurrentUserId().getId().equals(products.getUserId())) {
            products.setName(product.getName());
            products.setPrice(product.getPrice());
            products.setDescription(product.getDescription());
            if (product.getPrice() <= 0 || product.getName() == null)
                throw new HandException("Price not be negative or 0");
            productMapper.update(products);
            return products;
        } else throw new NotFoundException("Product not found");
    }

    public Products delete(Long id) {
        Products products = productMapper.findById(id);
        if (getCurrentUserId().getId().equals(products.getUserId())) {
            productMapper.deleteById(id);
        } else throw new NotFoundException("Product not found");
        return products;
    }

    public Products ShowId(Long id) {
        Products products = productMapper.findById(id);
        if (products == null) throw new NotFoundException("Product not found");
        products.setLikes(productMapper.countLike(id));
        products.setDislikes(productMapper.countDislike(id));
        products.setComments(commentService.showComment(id));
        productMapper.findById(id);
        return products;
    }

    public List<AllProducts> AllProducts(int pageNumber, int pageSize) {
        List<AllProducts> pageableProducts = allProductsService.findByPage(pageNumber, pageSize);
        Iterator<AllProducts> iterator = pageableProducts.stream().iterator();
        List<AllProducts> products = new ArrayList<>();
        iterator.forEachRemaining(products::add);
        return products;
    }
}
