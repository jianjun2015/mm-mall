package com.mmall.service.impl;

import com.mmall.common.ServerResponse;
import com.mmall.dao.ICategoryDao;
import com.mmall.pojo.TCategory;
import com.mmall.service.ICategoryService;
import com.mmall.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/29 18:08
 * @version: V1.0
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao iCategoryDao;

    @Override
    public ServerResponse<String> addCategory(String categoryName,int parentId) {

        TCategory tCategory = buildTCategory(categoryName,parentId);
        int resultCount = iCategoryDao.saveCategory(tCategory);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("保存目录信息失败");
        }

        return ServerResponse.createBySuccessMessage("添加成功");
    }

    private TCategory buildTCategory(String categoryName,int parentId){
        TCategory tCategory = new TCategory();

        tCategory.setName(categoryName);
        tCategory.setParentId(parentId);
        tCategory.setStatus(true);

        return tCategory;
    }
}
