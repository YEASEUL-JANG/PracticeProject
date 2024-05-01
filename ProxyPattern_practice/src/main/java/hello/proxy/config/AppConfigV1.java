package hello.proxy.config;

import hello.proxy.app.v1.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //@Component 어노테이션 포함 -> 컴포넌트 스캔의 대상
public class AppConfigV1 {
    @Bean
    public OrderControllerV1 orderControllerV1(){

        return new OrderControllerV1Impl(orderServiceV1());
    }

    @Bean
    public OrderServiceV1 orderServiceV1() {
        return new OrderServiceV1impl(orderRepositoryV1());
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1() {
        return new OrderRepositoryV1impl();
    }
}
