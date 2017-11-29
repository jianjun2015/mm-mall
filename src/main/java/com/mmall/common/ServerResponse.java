package com.mmall.common;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * @author: wangjianjun
 * @description: 指定的统一的返回信息，根据泛型来指定具体返回数据类型
 * @date: 2017/11/28 16:21
 * @version: V1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonSerialize保证序列化json的时候，如果是null的对象，key也会消失
public class ServerResponse<T> implements Serializable{

    private static final long serialVersionUID = 4206806056661931532L;

    private Integer satus;
    private String msg;
    private T data;

    public ServerResponse(Integer satus) {
        this.satus = satus;
    }

    private ServerResponse(Integer satus, String msg) {
        this.satus = satus;
        this.msg = msg;
    }

    private ServerResponse(Integer satus, T data) {
        this.satus = satus;
        this.data = data;
    }

    private ServerResponse(Integer satus, String msg, T data) {
        this.satus = satus;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.getSatus() == ResponseCode.SUCCESS.getCode();
    }

    public Integer getSatus() {
        return satus;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    /**
     * 成功返回
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 带msg的成功返回
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    /**
     *  带数据的成功返回
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    /**
     * 带msg、data的成功返回
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    /**
     * 错误返回
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode());
    }

    /**
     * 带errormsg的返回
     * @param errorMsg
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByErrorMessage(String errorMsg){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMsg);
    }

    /**
     * 指定errorCode和errorMsg的返回
     * @param errorCode
     * @param errorMsg
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMsg){
        return new ServerResponse<T>(errorCode,errorMsg);
    }
}
