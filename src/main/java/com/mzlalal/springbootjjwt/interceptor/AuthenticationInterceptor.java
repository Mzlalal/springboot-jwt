package com.mzlalal.springbootjjwt.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mzlalal.springbootjjwt.annotation.Token;
import com.mzlalal.springbootjjwt.annotation.TokenPass;
import com.mzlalal.springbootjjwt.entity.User;
import com.mzlalal.springbootjjwt.service.UserService;
import com.mzlalal.springbootjjwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


/**
 * @description:  验证拦截器
 *  过期时间 刷新什么都还没做呢
 * @author:       Mzlalal
 * @date:         2019/9/5 14:49
 * @version:      1.0
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenUtil jwtToken;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        // 检查是否有TokenPass注释，有则跳过认证
        // 现在是使用根据方法上面是否带有注解token 有则验证 没有的话不加TokenPass也能够执行好像写了没什么用
        // 拦截的都是带注解的除非使用全局都使用验证token 但这样的话token注解没用 写着玩吧0.0
        if (method.isAnnotationPresent(TokenPass.class)) {
            TokenPass passToken = method.getAnnotation(TokenPass.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 检查是否带有token注解并进行解析
        if (method.isAnnotationPresent(Token.class)) {
            Token userLoginToken = method.getAnnotation(Token.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("token已经过期,请重新登录");
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                User user = userService.findUserById(userId);
                if (user == null) {
                    throw new RuntimeException("用户不存在,请重新登录");
                }
                // 验证 token 使用用户密码作为私匙
                try {
                    jwtToken.parseToken(user, token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
