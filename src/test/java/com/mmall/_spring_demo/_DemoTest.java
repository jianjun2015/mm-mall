package com.mmall._spring_demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/28 10:16
 * @version: V1.0
 */
public class _DemoTest {

    public static void main(String[] args) {

        ApplicationContext atx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-demo.xml");
        _Demo demo = atx.getBean("demo", _Demo.class);
        _Demo demo1 = new _Demo();
        System.out.println(demo.getName());
        System.out.println(demo1.getName());
    }
}
