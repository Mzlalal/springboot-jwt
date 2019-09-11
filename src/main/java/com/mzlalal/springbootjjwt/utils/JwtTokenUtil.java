package com.mzlalal.springbootjjwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mzlalal.springbootjjwt.entity.User;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @description: jwt 工具类
 * @author: Mzlalal
 * @date: 2019/9/5 14:42
 * @version: 1.0
 */
@Component
@PropertySource("classpath:global.properties")
public class JwtTokenUtil {

    @Value("${jwt.secret: mySecret}")
    private String secret;

    @Value("${jwt.expiration: 30}")
    private int expiration;

    /**
     * 通过 user 获取 token
     * @param user
     * @return
     */
    public String createToken(User user) {
        // 将 user id 保存到 token 里面 以 password 作为 token 的密钥
        Date now = new Date();
        return JWT.create()
                // 签发时间
                .withIssuedAt(now)
                // 设置过期时间
                .withExpiresAt(DateUtils.addMinutes(now, expiration))
                // 接受者ID
                .withAudience(user.getId())
                // 使用密码作为私匙
                .sign(Algorithm.HMAC256(user.getPassword()));
    }

    /**
     * 通过 user 解析 token
     * @param user  用户
     * @param token token
     * @return
     */
    public DecodedJWT parseToken(User user, String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        return jwtVerifier.verify(token);
    }
}
