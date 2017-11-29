package com.mmall.dao.impl;

import com.mmall.dao.IUserDao;
import com.mmall.mapper.TUserMapper;
import com.mmall.pojo.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/28 17:41
 * @version: V1.0
 */
@Repository("iUserDao")
@Transactional(rollbackFor = Exception.class)
public class UserDaoImpl implements IUserDao{

    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public TUser selectByUsername(String username) {
        return tUserMapper.selectByUsername(username);
    }

    @Override
    public int checkEmail(String email) {
        return tUserMapper.selectByEmail(email);
    }

    @Override
    public int saveUser(TUser tUser) {

        tUser.setCreateTime(new Date());
        tUser.setUpdateTime(new Date());
        return tUserMapper.insert(tUser);
    }

    @Override
    public TUser selectByUsernamePassword(String username, String password) {
        return tUserMapper.selectByUsernamePassword(username,password);
    }

    @Override
    public int checkAnswer(String username, String question, String answer) {
        return tUserMapper.checkAnswer(username,question,answer);
    }

    @Override
    public int updatePasswordByUsername(String username, String password,Date date) {
        return tUserMapper.updatePassword(username,password,date);
    }

    @Override
    public int checkEmailByUserId(String email, Integer id) {
        return tUserMapper.checkEmailByUserId(email,id);
    }

    @Override
    public int updateUserById(TUser tUser) {
        return tUserMapper.updateByPrimaryKeySelective(tUser);
    }
}
