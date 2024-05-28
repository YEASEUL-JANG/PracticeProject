package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Pointcuts {

    //hello.aop.order 패키지와 하위패키지 모두포함
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder(){}


    //클래스 이름 패턴이 *Service 인것
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    //allOrder + allService 조합
    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}


}
