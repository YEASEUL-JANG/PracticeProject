package spring.SpringLogTrace_practice.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringLogTrace_practice.callback.TraceCallback;
import spring.SpringLogTrace_practice.callback.TraceTemplate;
import spring.SpringLogTrace_practice.trace.logtrace.LogTrace;
import spring.SpringLogTrace_practice.trace.template.AbstractTemplate;

@RestController //controller + responsebody
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate traceTemplate;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.traceTemplate = new TraceTemplate(trace);
    }

    //템플릿 콜백 패턴 적용
    @GetMapping("/v5/request")
    public String request(String itemId) {
        return traceTemplate.execute("OrderController.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        });
    }
}

