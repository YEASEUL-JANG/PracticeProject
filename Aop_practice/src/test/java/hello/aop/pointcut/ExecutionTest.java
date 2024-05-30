package hello.aop.pointcut;

import hello.aop.order.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;
    @BeforeEach//테스트 실행 전 설정
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }
    @Test
    void printMethod(){
        //public java.lang.String hello.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod : {}",helloMethod);
        //-> execution 으로 시작하는 포인트컷 표현식은 해당 메서드 정보를 매칭해서 포인트컷 대상을 찾아낸다.
    }

    @Test
    void exactMatch(){ //가장 세밀한 포인트컷
        //public java.lang.String hello.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        //접근제어자, 반환타입, 메서드이름(파라미터)
        pointcut.setExpression("execution(public String hello.aop.order.aop.member.MemberServiceImpl.hello(String))");
    //포인트컷이 메서드와 일치하는지
    Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void allMatch(){ //가장 생략된 포인트컷
        //public java.lang.String hello.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        //반환타입, 메소드이름, 파라미터 전부 생략
        pointcut.setExpression("execution(* *(..))");
        //포인트컷이 메서드와 일치하는지
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatch(){ //메서드명으로 match
        pointcut.setExpression("execution(* hello(..))");
        //포인트컷이 메서드와 일치하는지
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
    @Test
    void namePatternMatch(){ //패턴 메서드명으로 match
        pointcut.setExpression("execution(* hel*(..))");
        //포인트컷이 메서드와 일치하는지
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch(){ //패키지 정확 match
        pointcut.setExpression("execution(* hello.aop.order.aop.member.MemberServiceImpl.hello(..))");
        //포인트컷이 메서드와 일치하는지
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
    @Test
    void packageExactMatch2(){ //패키지* match
        pointcut.setExpression("execution(* hello.aop.order.aop.member.*.*(..))");
        //포인트컷이 메서드와 일치하는지
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch3(){ //서브패키지* match
        pointcut.setExpression("execution(* hello.aop.order..*.*(..))"); //..* 는 하위패키지까지 모두 포함
        //포인트컷이 메서드와 일치하는지
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

}
