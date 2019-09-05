package com.mzlalal.springbootjjwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:  
 * @author:       Mzlalal
 * @date:         2019/9/5 15:03
 * @version:      1.0
 */
@SpringBootApplication
@MapperScan("com.mzlalal.springbootjjwt.mapper")
public class SpringbootJwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootJwtApplication.class, args);
        System.out.println("启动完毕");
    }
}
