package com.mzlalal.springbootjjwt.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:  全局异常处理
 * @author:       Mzlalal
 * @date:         2019/9/5 16:03
 * @version:      1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        // 获取根信息
        String msg = ExceptionUtils.getRootCause(e).getMessage();

        // 如果根信息为空
        if (StringUtils.isBlank(msg)) {
            msg = "服务器出错";
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", msg);
        return jsonObject;
    }
}
