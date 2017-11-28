package com.mmall.dao;

import com.mmall.pojo.TUser;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/28 17:41
 * @version: V1.0
 */
public interface IUserDao {

    int checkUsername(String username);
    TUser selectByUsernamePassword(String username,String password);
}
