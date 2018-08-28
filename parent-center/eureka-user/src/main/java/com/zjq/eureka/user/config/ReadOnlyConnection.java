package com.zjq.eureka.user.config;



import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;

import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Target;
import com.zjq.eureka.user.config.DbContextHolder.DbType;


@Target({ElementType.METHOD,ElementType.TYPE})

@Retention(RetentionPolicy.RUNTIME)

public @interface ReadOnlyConnection {

DbType value() default DbContextHolder.DbType.READ1;

}
