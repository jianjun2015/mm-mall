package com.mmall.mapper;

import com.mmall.pojo.TCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCategory record);

    TCategory selectByPrimaryKey(Integer id);

    List<TCategory> selectAll();

    int updateByPrimaryKey(TCategory record);

    int updateCategoryNameById(@Param("categoryId") Integer categoryId, @Param("categoryName")String categoryName);

    List<TCategory> queryChildrenParallelCategoryById(@Param("categoryId")Integer categoryId);
}