package hello.proxy.config.v1_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {
    //실제 실행클래스를 구현한 프록시객체를 대신 끼워넣고 해당 프록시에서 실행클래스를 호출한다.

    @Bean
    public OrderControllerV2 orderController(LogTrace logTrace){
        OrderControllerV2 target = new OrderControllerV2(orderService(logTrace));
        return new OrderControllerConcreteProxy(logTrace,target);
    }

    @Bean
    public OrderServiceV2 orderService(LogTrace logTrace) {
        OrderServiceV2 target = new OrderServiceV2(orderRepository(logTrace));
        return new OrderServiceConcreteProxy(logTrace,target);
    }

    @Bean
    public OrderRepositoryV2 orderRepository(LogTrace logTrace) {
        OrderRepositoryV2 target = new OrderRepositoryV2();

        return new OrderRepositoryConcreteProxy(logTrace,target);
    }

}
