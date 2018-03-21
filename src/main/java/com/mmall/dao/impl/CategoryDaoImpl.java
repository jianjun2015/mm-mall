package com.mmall.dao.impl;

import com.mmall.dao.ICategoryDao;
import com.mmall.mapper.TCategoryMapper;
import com.mmall.pojo.TCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/29 18:08
 * @version: V1.0
 */
@Repository
public class CategoryDaoImpl implements ICategoryDao{

    @Autowired
    private TCategoryMapper tCategoryMapper;

    @Override
    public int saveCategory(TCategory tCategory) {

        tCategory.setCreateTime(new Date());
        tCategory.setUpdateTime(new Date());
        return tCategoryMapper.insert(tCategory);
    }

    @Override
    public int updateCategoryNameById(Integer categoryId, String categoryName) {
        return tCategoryMapper.updateCategoryNameById(categoryId,categoryName);
    }

    @Override
    public List<TCategory> queryChildrenParallelCategoryById(Integer categoryId) {
        return tCategoryMapper.queryChildrenParallelCategoryById(categoryId);
    }

    @Override
    public TCategory selectByPrimaryKey(Integer categoryId) {
        return tCategoryMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public List<TCategory> selectCategoryChildrenByParentId(Integer categoryId) {
        return tCategoryMapper.queryChildrenParallelCategoryById(categoryId);
    }
}
