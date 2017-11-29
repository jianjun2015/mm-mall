package com.mmall.mapper;

import com.mmall.pojo.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    TUser selectByPrimaryKey(Integer id);

    TUser selectByUsername(@Param("username") String username);

    int selectByEmail(@Param("email") String email);

    TUser selectByUsernamePassword(@Param("username")String username,@Param("password")String password);

    List<TUser> selectAll();

    int updateByPrimaryKey(TUser record);

    int checkAnswer(@Param("username")String username,
                    @Param("question")String question,
                    @Param("answer")String answer);

    int updatePassword(
            @Param("username")String username,
            @Param("password")String password,
            @Param("uptDate")Date uptDate);

    int updateByPrimaryKeySelective(TUser tUser);

    int checkEmailByUserId(@Param("email")String email,
                           @Param("id")int id);
}