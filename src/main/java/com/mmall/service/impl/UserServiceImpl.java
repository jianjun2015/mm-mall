package com.mmall.service.impl;

import com.mmall.common.ServerResponse;
import com.mmall.convert.TUserConvert;
import com.mmall.dao.IUserDao;
import com.mmall.pojo.TUser;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import com.mmall.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/28 17:31
 * @version: V1.0
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao iUserDao;

    @Override
    public ServerResponse<User> login(String username, String password) {
        if (iUserDao.checkUsername(username) == 0){
            return ServerResponse.createByErrorMessage("当前用户不存在");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        TUser tUser = iUserDao.selectByUsernamePassword(username, md5Password);
        if (tUser == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        User user = new TUserConvert().convert(tUser);

        return ServerResponse.createBySuccess("登录成功",user);
    }
}
