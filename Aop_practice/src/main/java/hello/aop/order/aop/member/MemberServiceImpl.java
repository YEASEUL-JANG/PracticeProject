package hello.aop.order.aop.member;

import hello.aop.order.aop.member.annotation.ClassAOP;
import hello.aop.order.aop.member.annotation.MethodAOP;
import org.springframework.stereotype.Component;

@ClassAOP
@Component //컴포넌트 스캔을 이용해서 등록함
public class MemberServiceImpl implements MemberService{

    @Override
    @MethodAOP("test value")
    public String hello(String param) {

        return "ok";
    }

    public String internal(String param){

        return "ok";
    }
}
