package spring.SpringLogTrace_practice.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.SpringLogTrace_practice.trace.logtrace.LogTrace;
import spring.SpringLogTrace_practice.trace.template.AbstractTemplate;

@RestController //controller + responsebody
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace;

//추상템플릿 적용
    @GetMapping("/v4/request")
    public String request(String itemId) {

        //추상템플릿 가져옴
        AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
        return template.execute("OrderController.request()");
    }
}

