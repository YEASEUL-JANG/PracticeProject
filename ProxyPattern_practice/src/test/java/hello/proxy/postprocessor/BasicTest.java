package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BasicTest {

    @Test
    void basicConfig(){
        //스프링 컨테이너 생성
        //BasicConfig 를 넣어서 스프링 빈으로 등록
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        //A는 빈으로 등록된다.
        A beanA = applicationContext.getBean("beanA", A.class);
        beanA.helloA();

        //B는 빈으로 등록되지 않는다.
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,() -> applicationContext.getBean(B.class));
    }
    @Slf4j
    @Configuration
    static class BasicConfig {
        @Bean(name = "beanA")
        public A a(){
            return new A();
        }
    }
    @Slf4j
    static class A {
        public void helloA(){
        log.info("Hello A");
        }
    }
    @Slf4j
    static class B {
        public void helloB(){
            log.info("Hello B");
        }
    }
}
