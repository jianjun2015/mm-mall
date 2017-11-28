package com.mmall.controller;

import com.mmall.common.Consts;
import com.mmall.common.ServerResponse;
import com.mmall.service.IUserService;
import com.mmall.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/28 18:32
 * @version: V1.0
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do",method = RequestMethod.GET)
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username,password);
        if (response.isSuccess()){
            session.setAttribute(Consts.CURRENT_USER,response.getData());
        }
        return response;
    }
}
