package com.mmall.mapper;

import com.mmall.pojo.TShipping;
import java.util.List;

public interface TShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TShipping record);

    TShipping selectByPrimaryKey(Integer id);

    List<TShipping> selectAll();

    int updateByPrimaryKey(TShipping record);
}