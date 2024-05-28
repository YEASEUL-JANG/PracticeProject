package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

//    @Around(("hello.aop.order.aop.Pointcuts.orderAndService()")) //이렇게 하면 여러군데에서 동일한 포인트컷을 사용할 수 있음
//    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
//        try{
//            //@Before
//            log.info("[트랜잭션 시작]{}",joinPoint.getSignature());
//            Object result = joinPoint.proceed();
//            //@AfterReturning
//            log.info("[트랜잭션 커밋]{}",joinPoint.getSignature());
//            return result;
//        }catch (Exception e){
//            //@AfterThrowing
//            log.info("[트랜잭션 롤백]{}",joinPoint.getSignature());
//            throw e;
//        }finally {
//            //@After
//            log.info("[리소스 릴리즈] {}",joinPoint.getSignature());
//        }
//    }
    @Before(("hello.aop.order.aop.Pointcuts.orderAndService()"))
    public void deBefore(JoinPoint joinPoint){//ProceedingJoinPoint 는 Around에서만 사용가능
        log.info("[before] {}",joinPoint.getSignature());
        //위의 로직이 끝나면 joinPoint를 그냥 실행해준다. (실제 타겟 자동 실행)
    }
    @AfterReturning(value = ("hello.aop.order.aop.Pointcuts.orderAndService()"),returning = "result")
    public void doReturn(JoinPoint joinPoint,Object result){//returning 값이랑 매칭되어 리턴값이 들어옴
        log.info("[AfterReturning] {}, result = {}",joinPoint.getSignature(), result);
        //위의 로직이 끝나면 joinPoint를 그냥 실행해준다. (실제 타겟 자동 실행)
    }
    @AfterThrowing(value = ("hello.aop.order.aop.Pointcuts.orderAndService()"),throwing = "ex")
    public void doThrowing(JoinPoint joinPoint,Exception ex){//returning 값이랑 매칭되어 리턴값이 들어옴
        log.info("[ex] {} message = {}", ex);
        //위의 로직이 끝나면 joinPoint를 그냥 실행해준다. (실제 타겟 자동 실행)
    }

    @After(value = ("hello.aop.order.aop.Pointcuts.orderAndService()"))
    public void doAfter(JoinPoint joinPoint){//returning 값이랑 매칭되어 리턴값이 들어옴
        log.info("[after] {} ", joinPoint.getSignature());
        //위의 로직이 끝나면 joinPoint를 그냥 실행해준다. (실제 타겟 자동 실행)
    }

}
