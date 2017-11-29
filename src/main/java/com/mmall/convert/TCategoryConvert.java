package com.mmall.convert;

import com.mmall.pojo.TCategory;
import com.mmall.util.converter.AbstractConverter;
import com.mmall.vo.Category;

/**
 * @author: wangjianjun
 * @description:
 * @date: 2017/11/29 18:09
 * @version: V1.0
 */
public class TCategoryConvert extends AbstractConverter<TCategory,Category> {

    @Override
    protected void assignValue(Category target, TCategory source) {
        target.setId(source.getId());
        target.setParentId(source.getParentId());
        target.setName(source.getName());
        target.setSortOrder(source.getSortOrder());
        target.setStatus(source.getStatus());
        target.setCreateTime(source.getCreateTime());
        target.setUpdateTime(source.getUpdateTime());
    }

    @Override
    protected Category initTarget() {
        return new Category();
    }
}
