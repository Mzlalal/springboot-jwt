package com.mzlalal.springbootjjwt.api;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mzlalal.springbootjjwt.annotation.Token;
import com.mzlalal.springbootjjwt.annotation.TokenPass;
import com.mzlalal.springbootjjwt.entity.User;
import com.mzlalal.springbootjjwt.service.UserService;
import com.mzlalal.springbootjjwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @description:  测试api
 * @author:       Mzlalal
 * @date:         2019/9/5 14:45
 * @version:      1.0
 */
@RestController
@RequestMapping("/api")
public class UserApi {
    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public JSONObject login(User user, HttpSession session) {
        JSONObject jsonObject = new JSONObject();

        // 根据用户名称查询用户
        User userForBase = userService.findByUsername(user);
        if (userForBase == null) {
            jsonObject.put("message", "登录失败,用户不存在");
            return jsonObject;
        } else {
            // 验证密码
            if (!userForBase.getPassword().equals(user.getPassword())) {
                jsonObject.put("message", "登录失败,密码错误");
                return jsonObject;
            } else {
                String token = jwtTokenUtil.createToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                session.setAttribute("accessUser", userForBase);
                return jsonObject;
            }
        }
    }

    @TokenPass
    @GetMapping("/getMessage")
    public String getMessage() {
        return "你是国家免检产品!";
    }

    @PostMapping("/verifyToken")
    public String verifyToken(String token, HttpSession session) {
        DecodedJWT jwt = jwtTokenUtil.parseToken(token);
        if (jwt != null) {
            return "成功";
        }
        return "失败";
    }
}
