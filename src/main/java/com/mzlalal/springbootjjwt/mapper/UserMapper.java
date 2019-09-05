package com.mzlalal.springbootjjwt.mapper;

import com.mzlalal.springbootjjwt.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @description:  用户mapper
 * @author:       Mzlalal
 * @date:         2019/9/5 14:47
 * @version:      1.0
 */
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return
     */
    User findByUsername(@Param("username") String username);

    /**
     * 根据用户ID 查询用户
     * @param Id 用户ID
     * @return
     */
    User findUserById(@Param("Id") String Id);
}
