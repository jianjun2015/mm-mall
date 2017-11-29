package com.mmall.controller.user;

import com.mmall.common.Consts;
import com.mmall.common.ServerResponse;
import com.mmall.service.IUserService;
import com.mmall.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/29 15:52
 * @version: V1.0
 */
@Controller
@RequestMapping("/manager/user/")
public class ManegerController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username,password);
        if (response.isSuccess()){
            if (Consts.Role.ROLE_ADMIN == response.getData().getRole()) {
                session.setAttribute(Consts.CURRENT_USER,response.getData());
                return response;
            }
            return ServerResponse.createByErrorMessage("你 不是管理员");
        }
        return response;
    }
}
