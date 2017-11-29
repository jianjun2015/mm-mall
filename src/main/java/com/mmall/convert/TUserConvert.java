package com.mmall.convert;

import com.mmall.pojo.TUser;
import com.mmall.util.converter.AbstractConverter;
import com.mmall.vo.User;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/28 18:23
 * @version: V1.0
 */
public class TUserConvert extends AbstractConverter<TUser,User> {

    @Override
    protected void assignValue(User target, TUser source) {
        target.setId(source.getId());
        target.setAnswer(source.getAnswer());
        target.setCreateTime(source.getCreateTime());
        target.setEmail(source.getEmail());
        target.setPhone(source.getPhone());
        target.setQuestion(source.getQuestion());
        target.setRole(source.getRole());
        target.setUpdateTime(source.getUpdateTime());
        target.setUsername(source.getUsername());
    }

    @Override
    protected User initTarget() {
        return new User();
    }
}
