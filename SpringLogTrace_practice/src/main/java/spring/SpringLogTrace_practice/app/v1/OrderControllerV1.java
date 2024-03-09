package spring.SpringLogTrace_practice.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringLogTrace_practice.trace.TraceStatus;
import spring.SpringLogTrace_practice.trace.hellotrace.HelloTraceV1;

@RestController //controller + responsebody
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String item) {

        TraceStatus status = trace.begin("OrderController.request()");
        try{
            orderService.orderItem(item);
            trace.end(status);
            return "OK";

        }catch (Exception e){
            trace.exception(status,e);
            throw e; // 예외를 던지지 않으면 controller에서 예외를 먹어버림-> 정상흐름에 방해
        }
    }
}

