package com.mzlalal.springbootjjwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:  免 token 验证
 * @author:       Mzlalal
 * @date:         2019/9/5 14:44
 * @version:      1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenPass {
    boolean required() default true;
}
