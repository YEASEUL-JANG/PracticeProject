package hello.proxy.app.v2;

import hello.proxy.app.v1.OrderServiceV1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequestMapping //@Controller를 쓰면 안애 @Component가 있어 자동으로 컴포넌트 대상이 되기 때문에 수동으로 등록하기 위해 RequestMapping 사용
@ResponseBody
public class OrderControllerV2  {
    private final OrderServiceV2 orderService;

    public OrderControllerV2(OrderServiceV2 orderServiceV2) {
        this.orderService = orderServiceV2;
    }


    @GetMapping("/v2/request")
    public String request(String itemId) {
      orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v2/no-log")
    public String noLog() {
        return "ok";
    }
}
