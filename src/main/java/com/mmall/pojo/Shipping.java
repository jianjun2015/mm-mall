package com.mmall.pojo;

import java.util.Date;

public class Shipping {
    private Integer id;

    private Integer userId;

    private String receiverName;

    private String receiverPhone;

    private String receiverMobile;

    private String receiverProvince;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private String receiverZip;

    private Date createTime;

    private Date updateTime;

    public Shipping(Integer id, Integer userId, String receiverName, String receiverPhone, String receiverMobile, String receiverProvince, String receiverCity, String receiverDistrict, String receiverAddress, String receiverZip, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverMobile = receiverMobile;
        this.receiverProvince = receiverProvince;
        this.receiverCity = receiverCity;
        this.receiverDistrict = receiverDistrict;
        this.receiverAddress = receiverAddress;
        this.receiverZip = receiverZip;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
}