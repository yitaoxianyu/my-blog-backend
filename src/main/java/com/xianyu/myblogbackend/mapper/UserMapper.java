package com.xianyu.myblogbackend.mapper;


import com.xianyu.myblogbackend.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void insert(User user);

    User getByUsername(@Param("username") String username);
}
