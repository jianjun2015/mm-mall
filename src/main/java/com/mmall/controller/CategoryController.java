package com.mmall.controller;

import com.mmall.common.Consts;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import com.mmall.vo.Category;
import com.mmall.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: wangjianjun
 * @description: 只有管理员有权限操作
 * @date: 2017/11/29 18:07
 * @version: V1.0
 */
@Controller
@RequestMapping("/manage/category/")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "add_category.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> addCategory(HttpSession session, String categoryName,
                                              @RequestParam(value = "parentId", defaultValue = "0") int parentId) {

        Object obj = session.getAttribute(Consts.CURRENT_USER);
        if (obj == null) {
            return ServerResponse.createByErrorMessage("当前未登录");
        }

        User currentUser = (User) obj;
        ServerResponse<String> checkAdminRole = iUserService.checkAdminRole(currentUser);
        if (checkAdminRole.isSuccess()) {
            // 管理员开始处理逻辑
            return iCategoryService.addCategory(categoryName, parentId);
        } else {
            return checkAdminRole;
        }
    }

    @RequestMapping(value = "set_category_name.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> setCategoryName(HttpSession session, Integer categoryId, String categoryName) {
        Object obj = session.getAttribute(Consts.CURRENT_USER);
        if (obj == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "当前未登录");
        }

        User currentUser = (User) obj;
        ServerResponse<String> checkAdminRole = iUserService.checkAdminRole(currentUser);
        if (checkAdminRole.isSuccess()) {
            // 管理员开始处理逻辑
            return iCategoryService.updateCategoryName(categoryId, categoryName);
        } else {
            return checkAdminRole;
        }
    }

    @RequestMapping(value = "get_category.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session,
                                                      @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        Object obj = session.getAttribute(Consts.CURRENT_USER);
        if (obj == null) {
            return ServerResponse.createByErrorMessage("当前未登录");
        }

        User currentUser = (User) obj;
        ServerResponse<String> checkAdminRole = iUserService.checkAdminRole(currentUser);
        if (checkAdminRole.isSuccess()) {
            // 管理员开始处理逻辑
            List<Category> categories = iCategoryService.getChildrenParallelCategoryById(categoryId);
            if (categories.isEmpty()) {
                return ServerResponse.createBySuccessMessage("未找到当前子分类");
            }
            return ServerResponse.createBySuccess(categories);
        } else {
            return checkAdminRole;
        }
    }

    @RequestMapping(value = "get_deep_category.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getChildrenAndDeepChildrenCategory(HttpSession session,
                                                      @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        Object obj = session.getAttribute(Consts.CURRENT_USER);
        if (obj == null) {
            return ServerResponse.createByErrorMessage("当前未登录");
        }

        User currentUser = (User) obj;
        ServerResponse<String> checkAdminRole = iUserService.checkAdminRole(currentUser);
        if (checkAdminRole.isSuccess()) {
            // 管理员开始处理逻辑
            return iCategoryService.getChildrenAndDeepChildrenCategory(categoryId);
        } else {
            return checkAdminRole;
        }
    }
}
