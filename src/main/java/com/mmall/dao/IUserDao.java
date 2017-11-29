package com.mmall.dao;

import com.mmall.pojo.TUser;

import java.util.Date;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/28 17:41
 * @version: V1.0
 */
public interface IUserDao {

    TUser selectByUsername(String username);
    int checkEmail(String email);
    int saveUser(TUser tUser);
    TUser selectByUsernamePassword(String username,String password);
    int checkAnswer(String username, String question, String answer);
    int updatePasswordByUsername(String username,String password, Date date);
    int checkEmailByUserId(String email,Integer id);
    int updateUserById(TUser tUser);
}
