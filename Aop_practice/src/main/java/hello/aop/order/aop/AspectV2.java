package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    //hello.aop.order 패키지와 하위패키지 모두포함
    @Pointcut("execution(* hello.aop.order..*(..))")
    //메서드 반환은 void 타입이어야 한다
    private void allOrder(){} //pointcut signature

    @Around(("allOrder()")) //이렇게 하면 여러군데에서 동일한 포인트컷을 사용할 수 있음
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}",joinPoint.getSignature()); //메서드정보
        return joinPoint.proceed();
    }
}
