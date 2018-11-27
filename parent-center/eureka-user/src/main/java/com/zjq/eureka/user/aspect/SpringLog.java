package com.zjq.eureka.user.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SpringLog {
	/** 模块 */
    String title() default "";
 
    /** 功能 */
    String action() default "";
    
    /** 请求地址 */
    String requestUrl();
}
