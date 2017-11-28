package com.mmall.mapper;

import com.mmall.pojo.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    TUser selectByPrimaryKey(Integer id);

    TUser selectByUsername(@Param("username") String username);

    TUser selectByUsernamePassword(@Param("username")String username,@Param("password")String password);

    List<TUser> selectAll();

    int updateByPrimaryKey(TUser record);
}