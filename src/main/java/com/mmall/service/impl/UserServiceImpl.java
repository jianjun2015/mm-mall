package com.mmall.service.impl;

import com.mmall.common.Consts;
import com.mmall.common.ServerResponse;
import com.mmall.common.TokenCache;
import com.mmall.convert.TUserConvert;
import com.mmall.dao.IUserDao;
import com.mmall.pojo.TUser;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import com.mmall.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/28 17:31
 * @version: V1.0
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao iUserDao;

    @Override
    public ServerResponse<User> login(String username, String password) {
        if (iUserDao.selectByUsername(username) == null){
            return ServerResponse.createByErrorMessage("当前用户不存在");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        TUser tUser = iUserDao.selectByUsernamePassword(username, md5Password);
        if (tUser == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        User user = new TUserConvert().convert(tUser);

        return ServerResponse.createBySuccess("登录成功",user);
    }

    @Override
    public ServerResponse<String> register(User user){
        ServerResponse validResponse = this.checkValid(user.getUsername(),Consts.USERNAME);
        if (!validResponse.isSuccess()){
            return validResponse;
        }

        validResponse = this.checkValid(user.getEmail(),Consts.EMALL);
        if (!validResponse.isSuccess()){
            return validResponse;
        }

        TUser tUser = buildTUser(user);
        int resultCount = iUserDao.saveUser(tUser);
        if (resultCount == 1){
            return ServerResponse.createBySuccessMessage("注册成功");
        }

        return ServerResponse.createByErrorMessage("注册失败");
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type){
        if(StringUtils.isNotBlank(type)){
            if (Consts.USERNAME.equals(type)){
                TUser tUser = iUserDao.selectByUsername(str);
                if (tUser != null){
                    return ServerResponse.createByErrorMessage("当前用户已经存在");
                }
            }else if (Consts.EMALL.equals(type)){
                int resultCount = iUserDao.checkEmail(str);
                if (resultCount > 0){
                    return ServerResponse.createByErrorMessage("邮箱已经存在");
                }
            }
        }else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    @Override
    public ServerResponse<String> getQuestionByUsername(String username) {
        TUser tUser = iUserDao.selectByUsername(username);
        if (tUser != null){
            return ServerResponse.createBySuccess(tUser.getQuestion());
        }
        return ServerResponse.createByErrorMessage("当前用户不存在");
    }

    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int resultCount = iUserDao.checkAnswer(username, question, answer);
        if (resultCount > 0){
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }

        return ServerResponse.createByErrorMessage("问题答案校验失败");
    }

    @Override
    public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken) {

        if (StringUtils.isBlank(forgetToken)){
            return ServerResponse.createByErrorMessage("Token必须要有");
        }

        String cacheToken = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if (!StringUtils.equals(cacheToken,forgetToken)){
            return ServerResponse.createByErrorMessage("token不一致");
        }

        TUser tUser = iUserDao.selectByUsername(username);
        if (tUser == null){
            return ServerResponse.createByErrorMessage("当前重重密码用户不存在");
        }

        int resultCount = iUserDao.updatePasswordByUsername(username, MD5Util.MD5EncodeUtf8(passwordNew),new Date());
        if (resultCount > 0){
            return ServerResponse.createBySuccessMessage("重置密码成功");
        }

        return ServerResponse.createByErrorMessage("重置密码失败");
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld,String username, String passwordNew) {
        TUser tUser = iUserDao.selectByUsernamePassword(username,MD5Util.MD5EncodeUtf8(passwordOld));
        if (tUser == null){
            return ServerResponse.createByErrorMessage("当前重重密码用户不存在");
        }

        int resultCount = iUserDao.updatePasswordByUsername(username, MD5Util.MD5EncodeUtf8(passwordNew),new Date());
        if (resultCount > 0){
            return ServerResponse.createBySuccessMessage("重置密码成功");
        }

        return ServerResponse.createByErrorMessage("重置密码失败");
    }

    @Override
    public ServerResponse<User> getUserInfoByUsername(String username) {
        TUser tUser = iUserDao.selectByUsername(username);
        if (tUser != null){
            return ServerResponse.createBySuccess(new TUserConvert().convert(tUser));
        }

        return ServerResponse.createByErrorMessage("未查询到用户信息");
    }

    @Override
    public ServerResponse<User> updateUser(User user) {
        int resultCount = iUserDao.checkEmailByUserId(user.getEmail(), user.getId());
        if (resultCount > 0){
            return ServerResponse.createByErrorMessage("email 已经存在");
        }

        TUser tUser = new TUser();
        tUser.setId(user.getId());
        tUser.setEmail(user.getEmail());
        tUser.setPhone(user.getPhone());
        tUser.setQuestion(user.getQuestion());
        tUser.setAnswer(user.getAnswer());

        resultCount = iUserDao.updateUserById(tUser);
        if (resultCount > 0){
            return ServerResponse.createByErrorMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    private TUser buildTUser(User user){
        TUser tUser = new TUser();
        tUser.setAnswer(user.getAnswer());
        tUser.setEmail(user.getEmail());
        tUser.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        tUser.setPhone(user.getPhone());
        tUser.setQuestion(user.getQuestion());
        tUser.setRole(user.getRole());
        tUser.setUsername(user.getUsername());

        return tUser;
    }
}
