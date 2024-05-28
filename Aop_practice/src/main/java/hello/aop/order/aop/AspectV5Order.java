package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class AspectV5Order {
    @Aspect
    @Order(2) //순서는 클래스단위(@Aspect)로만 지정할 수 있다. 낮을수록 먼저 실행.
    public static class LogAspect{
        @Around(("hello.aop.order.aop.Pointcuts.allOrder()")) //이렇게 하면 여러군데에서 동일한 포인트컷을 사용할 수 있음
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}",joinPoint.getSignature()); //메서드정보
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class TxAspect{
        @Around(("hello.aop.order.aop.Pointcuts.orderAndService()")) //이렇게 하면 여러군데에서 동일한 포인트컷을 사용할 수 있음
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try{
                log.info("[트랜잭션 시작]{}",joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 커밋]{}",joinPoint.getSignature());
                return result;
            }catch (Exception e){
                log.info("[트랜잭션 롤백]{}",joinPoint.getSignature());
                throw e;
            }finally {
                log.info("[리소스 릴리즈] {}",joinPoint.getSignature());
            }
        }
    }



}
