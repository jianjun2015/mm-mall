package com.mmall.common;

/**
 * @author: wangjianjun
 * @description: 静态常量
 * @date: 2017/11/28 17:14
 * @version: V1.0
 */
public class Consts {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMALL = "email";
    public static final String USERNAME = "username";

    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//管理员
    }
}
