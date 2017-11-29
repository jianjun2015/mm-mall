package com.mmall.controller;

import com.mmall.common.Consts;
import com.mmall.common.ServerResponse;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import com.mmall.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: wangjianjun
 * @description: 只有管理员有权限操作
 * @date: 2017/11/29 18:07
 * @version: V1.0
 */
@Controller
@RequestMapping("/category/")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "add_category.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> addCategory(HttpSession session, String categoryName,
                                              @RequestParam(value="parentId",defaultValue = "0") int parentId ){

        Object obj = session.getAttribute(Consts.CURRENT_USER);
        if (obj == null){
            return ServerResponse.createByErrorMessage("当前未登录");
        }

        User currentUser = (User) obj;
        ServerResponse<String> checkAdminRole = iUserService.checkAdminRole(currentUser);
        if (checkAdminRole.isSuccess()){
            // 管理员开始处理逻辑
            return iCategoryService.addCategory(categoryName,parentId);
        }else {
            return checkAdminRole;
        }
    }
}
