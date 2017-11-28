package com.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author: wangjianjun
 * @description: 通过guawa进行缓存管理
 * @date: 2017/11/28 17:06
 * @version: V1.0
 */
public class TokenCache{

    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    private static final String TOKEN_PREFIX = "token_";

    //LRU算法
    private static LoadingCache<String,String> localCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)
            .maximumSize(10000)
            .expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key,String value){
        localCache.put(TOKEN_PREFIX+key,value);
    }

    public static String getKey(String key){
        String value = null;

        try {
            value = localCache.get(TOKEN_PREFIX+key);
            if ("null".equals(value)){
                return null;
            }
            return value;
        } catch (ExecutionException e) {
            logger.error("localCahe get error",e);
        }

        return null;
    }
}
