package com.mmall._test_demo;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/28 10:23
 * @version: V1.0
 */
public class M {

    public static void main(String[] args) {
        A a1 = new A();

        System.out.println(a1.i);
        System.out.println(a2.i);
    }

    static A a2 = new A();
}

class A{
    public A() {
        i=(j++ != 0)? ++j:--j;
    }
    public int i;
    public static int j=0;
}
