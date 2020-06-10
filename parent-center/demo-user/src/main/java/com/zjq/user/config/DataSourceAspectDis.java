package com.zjq.user.config;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.zjq.user.config.DbContextHolder.DbType;

@Component
@Aspect
public class DataSourceAspectDis {
	
	@Pointcut("@annotation(com.zjq.user.config.ReadOnlyConnection)")
	public void dataSourcePointcut() {

	}

	@Around("dataSourcePointcut()")
	public Object doAround(ProceedingJoinPoint pjp) {

		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();

		Method method = methodSignature.getMethod();

		ReadOnlyConnection typeAnno = method.getAnnotation(ReadOnlyConnection.class);

		DbType sourceEnum = typeAnno.value();

		if (sourceEnum == DbType.WRITE) {

			DbContextHolder.setDbType(DbType.WRITE);

		} else if (sourceEnum == DbType.READ1) {

			DbContextHolder.setDbType(DbType.READ1);

		}

		Object result = null;

		try {

			result = pjp.proceed();

		} catch (Throwable throwable) {

			throwable.printStackTrace();

		} finally {

			DbContextHolder.resetDbType();

		}

		return result;

	}
}
