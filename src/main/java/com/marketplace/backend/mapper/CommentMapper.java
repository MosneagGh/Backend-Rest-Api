package com.marketplace.backend.mapper;

import com.marketplace.backend.model.Comment;
import com.marketplace.backend.model.CommentJoin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO comments(productid, userid, messages) values(#{productId}, #{userId}, #{message})")
    void save(Comment comm);

    @Select("SELECT id, productid, userid as user, messages as comment from comments where productid=#{productId}")
    List<Comment> allComments(Long productid);

    @Update("UPDATE comments set messages=#{message} WHERE id=#{id}")
    void update(Comment comment);

    @Delete("DELETE from comments where id =#{id}")
    void deleteById(Long commentsid);

    @Select("select id, productid as productId, userid as userId, messages as message from comments where id=#{id}")
    Comment findById(Long id);

    @Select("SELECT comments.id, productid, products.user_id from comments join products on (comments.productid = products.id ) where comments.id =#{id}")
    CommentJoin join(Long id);
}