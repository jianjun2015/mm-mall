package com.mmall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangjianjun on 2017/11/24.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("index")
    public String login(HttpServletRequest request, HttpServletResponse response){

        logger.trace("index controller trace");
        logger.debug("index controller debug");
        logger.info("index controller info");
        logger.warn("index controller warn");
        logger.error("index controller error");

        return "index";
    }
}
