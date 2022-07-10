package pratice.roon.misedirt.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ExecutionTimeLogger {

    //@Around("execution(* pratice.roon.misedirt.openApi.service.ApiCallService*.*(..))")
    @Around("@annotation(LogExecutionTime)")
    public void logAround(ProceedingJoinPoint joinPoint) {
        long start = System.nanoTime();

        try {
            joinPoint.proceed();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        long end = System.nanoTime();

        //TODO : reflection이 비싼 연산이라던데 실행시간 측정하다가 성능에 영향 주게 되는건가
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();

        log.info( methodName + "= " + (end - start) / 1_000_000 + " ms");
    }
}
