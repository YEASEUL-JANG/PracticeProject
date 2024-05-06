package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA(){
        AInterface target = new AImpl();
        //동적프록시가 호출할 대상타겟 주입
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        //AInterface를 구현한 동적프록시 생성-> 핸들러의 invoke 메서드를 실행함.
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                handler);

        proxy.call();

    }
    @Test
    void dynamicB(){
        BInterface target = new BImpl();
        //동적프록시가 호출할 대상타겟 주입
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        //BInterface를 구현한 동적프록시 생성-> 핸들러의 invoke 메서드를 실행함.
        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(),
                new Class[]{BInterface.class},
                handler);

        proxy.call();

    }
}
