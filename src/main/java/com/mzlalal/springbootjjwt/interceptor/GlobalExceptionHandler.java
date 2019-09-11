package com.mzlalal.springbootjjwt.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.TokenExpiredException;
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
    @ExceptionHandler(TokenExpiredException.class)
    public JSONObject handleTokenExpiredException(TokenExpiredException e) {
        return setErrorMsg("token 过期!", e);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JSONObject handleException(Exception e) {
        return setErrorMsg("服务繁忙!", e);
    }

    public JSONObject setErrorMsg (String message, Exception e) {
        // 获取根信息
        String msg = ExceptionUtils.getRootCauseMessage(e);

        // 如果根信息为空
        if (StringUtils.isBlank(msg)) {
            msg = message;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", msg);
        return jsonObject;
    }
}
