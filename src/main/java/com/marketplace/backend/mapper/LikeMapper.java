package com.marketplace.backend.mapper;

import com.marketplace.backend.model.Like;
import com.marketplace.backend.model.LikeJoin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface LikeMapper {

    @Select("select isliked as isLiked, productid as productId, userid as userId, id from rating where userid=#{userId} and productid=#{productId}")
    Like findByUserIdAndProdId(Long userId, Long productId);

    @Delete("delete from rating where id=#{id}")
    void deleteById(Long id);

    @Select("update rating set isliked = false where  productid =#{productId} and  userid =#{userId}")
    void dislikeProduct(Long productId, Long userId);

    @Select("update rating set isliked = true where productid =#{productId} and userid =#{userId}")
    void likeProduct(Long productId, Long userId);

    @Insert("insert into rating(isliked, productid, userid) values(#{isLiked}, #{productId}, #{userId})")
    void save(Like like);

    @Insert("select rating.productid, products.user_id from rating join products on (rating.productid = products.id) where rating.productid = #{productId}")
    LikeJoin join(Long id);
}
