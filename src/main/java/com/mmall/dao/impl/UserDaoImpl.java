package com.mmall.dao.impl;

import com.mmall.dao.IUserDao;
import com.mmall.mapper.TUserMapper;
import com.mmall.pojo.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/28 17:41
 * @version: V1.0
 */
@Repository
public class UserDaoImpl implements IUserDao{

    @Autowired
    private TUserMapper userMapper;

    @Override
    public int checkUsername(String username) {
        TUser tUser = userMapper.selectByUsername(username);
        if (tUser == null){
            return 0;
        }
        return 1;
    }

    @Override
    public TUser selectByUsernamePassword(String username, String password) {
        return userMapper.selectByUsernamePassword(username,password);
    }
}
