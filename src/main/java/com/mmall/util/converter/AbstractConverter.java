package com.mmall.util.converter;


import com.mmall.exception.ConversionException;

/**
 * Created by houjun on 2016-5-5.
 */
public abstract class AbstractConverter<S, T> implements Converter<S, T> {

    @Override
    public T convert(S source) throws ConversionException {
        if (source == null)
            return null;
        T target = initTarget();
        if (target == null)
            throw new ConversionException("实例化的target不能为空.");

        assignValue(target, source);
        return target;
    }

    /**
     * 赋值
     *
     * @param target 目标对象,not null
     * @param source 起始对象, not null
     */
    protected abstract void assignValue(T target, S source);


    /**
     * @return 目标对象的初始化
     */
    protected abstract T initTarget();
}
