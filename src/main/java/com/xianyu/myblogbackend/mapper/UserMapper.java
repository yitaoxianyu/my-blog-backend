package com.xianyu.myblogbackend.mapper;


import com.xianyu.myblogbackend.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insert(User user);
}
