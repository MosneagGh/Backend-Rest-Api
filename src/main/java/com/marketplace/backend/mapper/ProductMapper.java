package com.marketplace.backend.mapper;

import com.github.pagehelper.Page;
import com.marketplace.backend.model.AllProducts;
import com.marketplace.backend.model.Products;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductMapper {

    @Insert("INSERT INTO products(name,price,description,user_id) VALUES(#{name},#{price},#{description},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Products products);

    @Select("SELECT id,name,price,description,user_id as userId FROM products WHERE id = #{id}")
    Products findById(Long id);

    @Delete("DELETE from products where id = #{id}")
    void deleteById(Long id);

    @Update("UPDATE products set name=#{name}, price=#{price}, description=#{description} WHERE id = #{id}")
    void update(Products products);

    @Select("SELECT id,name,price,description, user_id as userId FROM products")
    Page<AllProducts> findByPage();

    @Select("SELECT count(userid) as like FROM rating where productid=#{productId} and isliked = true")
    long countLike(Long productid);

    @Select("SELECT count(userid) as dislike FROM rating where productid=#{productId} and isliked = false")
    long countDislike(Long productid);
}

