package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class AspectV1 {

    @Around(("execution(* hello.aop.order..*(..))"))
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}",joinPoint.getSignature()); //메서드정보
        return joinPoint.proceed();
    }
}
