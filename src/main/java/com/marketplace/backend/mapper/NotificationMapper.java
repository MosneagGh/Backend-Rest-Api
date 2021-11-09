package com.marketplace.backend.mapper;

import com.marketplace.backend.model.Notification;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NotificationMapper {
    @Select("Select * from notification where touser = #{toUser}")
    List<Notification> allNotification(Long toUser);

    @Insert("Insert into notification(userid, messages, createdat, touser) values (#{userId}, #{messages},#{createdAt},#{toUser})")
    void save(Notification notification);

    @Select("Select * from notification where touser = #{toUser} and id = #{id}")
    Notification findByIdAndUser(Long toUser, Long id);

    @Delete("Delete from notification where userid = touser")
    void delete();
}
