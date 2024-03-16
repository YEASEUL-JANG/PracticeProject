package spring.SpringLogTrace_practice.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.SpringLogTrace_practice.trace.TraceId;
import spring.SpringLogTrace_practice.trace.TraceStatus;
import spring.SpringLogTrace_practice.trace.hellotrace.HelloTraceV2;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;


    public void orderItem(TraceId traceId, String item){

        TraceStatus status = trace.beginSync(traceId,"OrderService.orderItem()");
        try{
            orderRepository.save(status.getTraceId(),item);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }

    }
}
