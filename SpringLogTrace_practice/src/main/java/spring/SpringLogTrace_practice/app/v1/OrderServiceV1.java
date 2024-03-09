package spring.SpringLogTrace_practice.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.SpringLogTrace_practice.trace.TraceStatus;
import spring.SpringLogTrace_practice.trace.hellotrace.HelloTraceV1;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 trace;


    public void orderItem(String item){

        TraceStatus status = trace.begin("OrderService.orderItem()");
        try{
            orderRepository.save(item);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }

    }
}
