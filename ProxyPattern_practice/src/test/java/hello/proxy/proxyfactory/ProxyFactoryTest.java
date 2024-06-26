package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시를 사용")
    void interfaceProxy(){
        ServiceInterface target = new ServiceImpl();
        //프록시팩토리 생성시에 실제 실행시킬 타겟 객체를 넣어준다.
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        //프록시 꺼내줌
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass = {}",target.getClass());
        log.info("proxyClass = {}",proxy.getClass());

        proxy.save();
    }

    @Test
    @DisplayName("구체클래스만 있으면 CGLIB를 사용")
    void concreteProxy(){
        ConcreteService target = new ConcreteService();
        //프록시팩토리 생성시에 실제 실행시킬 타겟 객체를 넣어준다.
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        //프록시 꺼내줌
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
        log.info("targetClass = {}",target.getClass());
        log.info("proxyClass = {}",proxy.getClass());


        proxy.call();
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고 클래스 기반 프록시를 사용")
    void proxyTargetProxy(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        //ProxyTargetClass(true) 는 항상 CGLIB 기반으로 타겟클래스 기반의 프록시를 생성한다.
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new TimeAdvice());
        //프록시 꺼내줌
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        log.info("targetClass = {}",target.getClass());
        log.info("proxyClass = {}",proxy.getClass());

        proxy.save();
    }

}
