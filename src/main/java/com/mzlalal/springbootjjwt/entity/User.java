package com.mzlalal.springbootjjwt.entity;

import lombok.*;

/**
 * @description:  用户实体
 * @author:       Mzlalal
 * @date:         2019/9/5 14:45
 * @version:      1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    /**
     * 用户ID
     */
    String Id;
    /**
     * 用户名
     */
    String username;
    /**
     * 密码
     */
    String password;
}
