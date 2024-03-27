package spring.SpringLogTrace_practice.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.SpringLogTrace_practice.callback.TraceCallback;
import spring.SpringLogTrace_practice.callback.TraceTemplate;
import spring.SpringLogTrace_practice.trace.logtrace.LogTrace;
import spring.SpringLogTrace_practice.trace.template.AbstractTemplate;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate traceTemplate;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.traceTemplate = new TraceTemplate(trace);
    }

    public void orderItem(String itemId){
        traceTemplate.execute("OrderService.orderitem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
