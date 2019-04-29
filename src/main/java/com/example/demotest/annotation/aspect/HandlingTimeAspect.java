package com.example.demotest.annotation.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class HandlingTimeAspect {

    @Pointcut("@annotation(com.example.demotest.annotation.HandlingTime)")
    public void handlingTimePointcut() {}

    @Around("handlingTimePointcut()")
    public Object handlingTimeAround(ProceedingJoinPoint joinPoint) throws Exception {
        try {
            long startTime = System.currentTimeMillis();
            Object proceed = joinPoint.proceed();
            System.out.println(proceed);
            System.out.println("方法执行时间：" + (System.currentTimeMillis() - startTime));
            return proceed;
        } catch (NullPointerException e){
            log.error(e.getMessage(), e);
            throw new NullPointerException(e.getMessage());
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage());
        }catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
            throwable.printStackTrace();
        }
        return null;
    }
}
