package com.yuchengxin.test.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：ycx
 * @version ：1.0
 * @date ：2020/3/25 21:24
 * @description ：需要登录才能进行操作的注解UserLoginToken
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}
