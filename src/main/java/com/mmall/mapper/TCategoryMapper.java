package com.mmall.mapper;

import com.mmall.pojo.TCategory;
import java.util.List;

public interface TCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCategory record);

    TCategory selectByPrimaryKey(Integer id);

    List<TCategory> selectAll();

    int updateByPrimaryKey(TCategory record);
}