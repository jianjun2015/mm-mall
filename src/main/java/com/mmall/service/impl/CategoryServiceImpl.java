package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.convert.TCategoryConvert;
import com.mmall.dao.ICategoryDao;
import com.mmall.pojo.TCategory;
import com.mmall.service.ICategoryService;
import com.mmall.util.converter.ConverterUtil;
import com.mmall.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Override
    public ServerResponse<String> updateCategoryName(Integer categoryId, String categoryName) {
        int resultCount = iCategoryDao.updateCategoryNameById(categoryId,categoryName);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("更新失败");
        }
        return ServerResponse.createByErrorMessage("更新成功");
    }

    @Override
    public List<Category> getChildrenParallelCategoryById(Integer categoryId) {

        List<TCategory> tCategories =  iCategoryDao.queryChildrenParallelCategoryById(categoryId);
        if(tCategories.isEmpty()){
            return new ArrayList<>();
        }

        List<Category> categories = ConverterUtil.convert(tCategories,new TCategoryConvert());
        return categories;
    }

    @Override
    public ServerResponse getChildrenAndDeepChildrenCategory(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);

        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null){
            for (Category categoryItem : categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    //递归算法算出子节点
    //使用Set结构可以排重，但是要重写hashcode和equals方法
    private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId){
        TCategory tCategory = iCategoryDao.selectByPrimaryKey(categoryId);
        Category category = new TCategoryConvert().convert(tCategory);
        if (category != null){
            categorySet.add(category);
        }
        //递归算法一定要有结束条件：当没有查到子节点时，就不进入for循环，直接返回。
        //mybatis对查询结果为空的处理：不返回null对象，但是size=0
        List<Category> categoryList = ConverterUtil.convert(iCategoryDao.selectCategoryChildrenByParentId(categoryId),new TCategoryConvert());
        for (Category categoryItem : categoryList){
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }

    private TCategory buildTCategory(String categoryName,int parentId){
        TCategory tCategory = new TCategory();

        tCategory.setName(categoryName);
        tCategory.setParentId(parentId);
        tCategory.setStatus(true);

        return tCategory;
    }
}
