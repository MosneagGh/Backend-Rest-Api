package com.marketplace.backend.mapper;

import com.marketplace.backend.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper {
    @Select("select * from users where username=#{username}")
    User findByUsername(String username);
    @Insert("insert into users(username,password, email) values(#{username}, #{password}, #{email})" )
    @Options(useGeneratedKeys=true, keyProperty="id")
    void save(User user);
}