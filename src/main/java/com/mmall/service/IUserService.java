package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.User;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/28 17:30
 * @version: V1.0
 */
public interface IUserService {

    ServerResponse<User> login(String username,String password);
}
