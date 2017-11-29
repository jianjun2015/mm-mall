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
    ServerResponse<String> register(User user);
    ServerResponse<String> checkValid(String str, String type);
    ServerResponse<String> getQuestionByUsername(String username);
    ServerResponse<String> checkAnswer(String username,String question,String answer);
    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);
    ServerResponse<String> resetPassword(String passwordOld,String username,String passwordNew);
    ServerResponse<User> getUserInfoByUsername(String username);
    ServerResponse<User> updateUser(User user);
    ServerResponse<String> checkAdminRole(User user);
}
