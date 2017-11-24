package com.mmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangjianjun on 2017/11/24.
 */
@Controller("/")
public class IndexController {

    @RequestMapping("index.jsp")
    public String login(HttpServletRequest request, HttpServletResponse response){
        return "index";
    }
}
