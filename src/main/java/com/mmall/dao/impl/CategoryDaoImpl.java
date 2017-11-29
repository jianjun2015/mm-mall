package com.mmall.dao.impl;

import com.mmall.dao.ICategoryDao;
import com.mmall.mapper.TCategoryMapper;
import com.mmall.pojo.TCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

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
}
