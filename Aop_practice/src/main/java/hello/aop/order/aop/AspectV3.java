package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    //hello.aop.order 패키지와 하위패키지 모두포함
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder(){}


    //클래스 이름 패턴이 *Service 인것
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService(){}

    @Around(("allOrder()")) //이렇게 하면 여러군데에서 동일한 포인트컷을 사용할 수 있음
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}",joinPoint.getSignature()); //메서드정보
        return joinPoint.proceed();
    }

    //hello.aop.order 패키지와 하위패키지이면서 클래스 이름패턴이 *Service
    @Around(("allOrder() && allService()")) //이렇게 하면 여러군데에서 동일한 포인트컷을 사용할 수 있음
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
