package com.mmall.dao;

import com.mmall.pojo.TCategory;

import java.util.List;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/29 18:08
 * @version: V1.0
 */
public interface ICategoryDao {

    int saveCategory(TCategory tCategory);
    int updateCategoryNameById(Integer categoryId,String categoryName);
    List<TCategory> queryChildrenParallelCategoryById(Integer categoryId);
    TCategory selectByPrimaryKey(Integer categoryId);
    List<TCategory> selectCategoryChildrenByParentId(Integer categoryId);
}
