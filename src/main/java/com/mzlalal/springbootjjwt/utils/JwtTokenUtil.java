package com.mzlalal.springbootjjwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mzlalal.springbootjjwt.entity.User;


/**
 * @description: jwt 工具类
 * @author: Mzlalal
 * @date: 2019/9/5 14:42
 * @version: 1.0
 */
public class JwtTokenUtil {
    /**
     * 通过 user 获取 token
     * @param user
     * @return
     */
    public static String createToken(User user) {
        // 将 user id 保存到 token 里面 以 password 作为 token 的密钥
        return JWT.create().withAudience(user.getId()).sign(Algorithm.HMAC256(user.getPassword()));
    }

    /**
     * 通过 user 解析 token
     * @param user  用户
     * @param token token
     * @return
     */
    public static void parseToken(User user, String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        jwtVerifier.verify(token);
    }
}
