package com.mmall.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author: wangjianjun
 * @description: 读取配置文件的便变量信息
 * @date: 2017/11/28 17:21
 * @version: V1.0
 */
public class PropertiesUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props;

    static {
        String fileName = "config/mm_mall.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(
                    PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName)
            ));
        } catch (IOException e) {
            logger.error("配置文件读取异常",e);
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key,String defaultValue){
        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value)){
            return defaultValue;
        }
        return value.trim();
    }
}
