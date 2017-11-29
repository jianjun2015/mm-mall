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
 * @date: 2017/11/28 18:32
 * @version: V1.0
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username,password);
        if (response.isSuccess()){
            session.setAttribute(Consts.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "register.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }

    @RequestMapping(value = "check_valid.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> checkValid(String string,String type){
        return iUserService.checkValid(string,type);
    }

    @RequestMapping(value = "get_user_info.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        Object obj = session.getAttribute(Consts.CURRENT_USER);
        if (obj == null){
            return ServerResponse.createByErrorMessage("当前未登录");
        }

        return ServerResponse.createBySuccess((User) obj);
    }

    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.getQuestionByUsername(username);
    }

    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer){
        return iUserService.checkAnswer(username,question,answer);
    }

    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken){
        return iUserService.forgetResetPassword(username,passwordNew,forgetToken);
    }

    @RequestMapping(value = "reset_password.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> resetPassword(String username,String passwordOld,String passwordNew,HttpSession session){
        Object obj = session.getAttribute(Consts.CURRENT_USER);
        if (obj == null){
            return ServerResponse.createByErrorMessage("当前用户未登录");
        }
        return iUserService.resetPassword(passwordOld,username,passwordNew);
    }

    @RequestMapping(value = "update_user_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateUserInformation(User user,HttpSession session){
        Object obj = session.getAttribute(Consts.CURRENT_USER);
        if (obj == null){
            return ServerResponse.createByErrorMessage("当前用户未登录");
        }

        User currentUser = (User) obj;
        user.setId(currentUser.getId());

       return iUserService.updateUser(user);
    }

    @RequestMapping(value = "logout.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        Object obj = session.getAttribute(Consts.CURRENT_USER);
        if (obj == null){
            return ServerResponse.createByErrorMessage("当前用户未登录");
        }
        session.removeAttribute(Consts.CURRENT_USER);
        return ServerResponse.createBySuccessMessage("成功退出");
    }

}
