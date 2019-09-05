package com.mzlalal.springbootjjwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:  token 注解
 * @author:       Mzlalal
 * @date:         2019/9/5 14:44
 * @version:      1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
    boolean required() default true;
}
