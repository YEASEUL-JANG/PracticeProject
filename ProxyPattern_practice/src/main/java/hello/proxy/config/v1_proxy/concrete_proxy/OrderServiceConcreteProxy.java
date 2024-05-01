package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

public class OrderServiceConcreteProxy extends OrderServiceV2 {
    private final LogTrace logTrace;
    private final OrderServiceV2 target;

    public OrderServiceConcreteProxy(LogTrace logTrace, OrderServiceV2 target) {
        //부모클래스를 상속 받을 때 부모클래스의 생성자도 같이 호출되어야 함.
        //하지만 얘는 프록시로서의 기능만 할것이고 부모기능이 필요없기 때문에 null을 주입함
        //기본문법 : 원래는 super();이 생략되어 있고 이는 부모클래스의 기본생성자를 호출하게 되나,
        // 현재 OrderServiceV2객체에 기본생성자가 없으므로 오류남.
        super(null);
        this.logTrace = logTrace;
        this.target = target;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try{
            status =  logTrace.begin("OrderService.request()");
            //target 호출
            target.orderItem(itemId);
            logTrace.end(status);
        }catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }
    }
}
