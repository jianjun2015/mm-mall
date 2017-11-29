package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.Category;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/29 18:07
 * @version: V1.0
 */
public interface ICategoryService {

    ServerResponse<String> addCategory(String categoryName,int parentId);
}
