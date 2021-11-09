package com.marketplace.backend.service;

import com.marketplace.backend.mapper.ProductMapper;
import com.github.pagehelper.PageHelper;
import com.marketplace.backend.model.AllProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AllProductsService {
    @Autowired
    private ProductMapper productMapper;

    public List<AllProducts> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return productMapper.findByPage();
    }
}

