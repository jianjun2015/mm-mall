package com.mmall.log;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: wangjianjun
 * @description: 日志父类
 * @date: 2017/11/24 17:12
 * @version: V1.0
 */
public class MallLogger {

    protected Logger logger;

    public MallLogger() {
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public void debugInfo(String methodName, Object request, Object resp) {
        StringBuilder sb = new StringBuilder();
        sb.append("###接口名:" + methodName).append(System.lineSeparator());
        sb.append("####请求数据:").append(JSON.toJSONString(request));
        sb.append(System.lineSeparator());
        sb.append("####响应数据:").append(resp == null ? "" : JSON.toJSONString(resp));
        sb.append(System.lineSeparator());
        logger.debug(sb.toString());
    }
}
