package com.zjq.eureka.user.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LogAspect {
	//切入点
    @Pointcut(value = "@annotation(com.zjq.eureka.user.aspect.SpringLog)")
    private void pointcut() {
    	log.info("进入切入点！" );
    }

    /**
     * 在方法执行前
     *
     * @param point
     * @param myLog
     * @return
     */
    @Around(value = "pointcut() && @annotation(myLog)")
    public Object around(ProceedingJoinPoint point, SpringLog myLog) {

    	log.info("执行了around方法");

        String requestUrl = myLog.requestUrl();
        String title = myLog.title();
        String action = myLog.action();

        //拦截的类名
        Class<? extends Object> clazz = point.getTarget().getClass();
        //拦截的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        log.info("执行了 类:" + clazz + " 方法:" + method + " 自定义请求地址:" + requestUrl);
        log.info("请求模块："  +title+ "功能描述：" + action );
        try {
            return point.proceed(); //执行程序
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
    }

    /**
     * 方法执行后
     *
     * @param joinPoint
     * @param myLog
     * @param result
     * @return
     */
    @AfterReturning(value = "pointcut() && @annotation(myLog)", returning = "result")
    public Object afterReturning(JoinPoint joinPoint, SpringLog myLog, Object result) {

//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();

    	log.info("执行了afterReturning方法");

    	log.info("执行结果：" + result);

        return result;
    }

    /**
     * 方法执行后 并抛出异常
     *
     * @param joinPoint
     * @param myLog
     * @param ex
     */
    @AfterThrowing(value = "pointcut() && @annotation(myLog)", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, SpringLog myLog, Exception ex) {
    	log.info("执行了afterThrowing方法");
    	log.info("请求：" + myLog.requestUrl() + " 出现异常");
    }

}
