package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.Category;

import java.util.List;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/29 18:07
 * @version: V1.0
 */
public interface ICategoryService {

    ServerResponse<String> addCategory(String categoryName,int parentId);

    ServerResponse<String> updateCategoryName(Integer categoryId,String categoryName);

    List<Category> getChildrenParallelCategoryById(Integer categoryId);

    ServerResponse getChildrenAndDeepChildrenCategory(Integer categoryId);
}
