package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {
    //이제 구현체 객체가 아닌 프록시객체를 대신 끼워넣고 그 프록시 객체에서
    //실제 구현체 객체를 불러올 수 있게 한다.

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace){
        OrderControllerV1Impl target = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(target,logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        OrderServiceV1impl target = new OrderServiceV1impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(target,logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1impl target = new OrderRepositoryV1impl();

        return new OrderRepositoryInterfaceProxy(target,logTrace);
    }

}
