package spring.SpringLogTrace_practice.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringLogTrace_practice.trace.TraceStatus;
import spring.SpringLogTrace_practice.trace.hellotrace.HelloTraceV1;
import spring.SpringLogTrace_practice.trace.hellotrace.HelloTraceV2;

@RestController //controller + responsebody
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String item) {

        TraceStatus status = trace.begin("OrderController.request()");
        try{
            orderService.orderItem(status.getTraceId(),item);
            trace.end(status);
            return "OK";

        }catch (Exception e){
            trace.exception(status,e);
            throw e; // 예외를 던지지 않으면 controller에서 예외를 먹어버림-> 정상흐름에 방해
        }
    }
}

