package spring.SpringLogTrace_practice.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.SpringLogTrace_practice.callback.TraceTemplate;
import spring.SpringLogTrace_practice.trace.logtrace.LogTrace;
import spring.SpringLogTrace_practice.trace.template.AbstractTemplate;

@Repository //ComponentScan의 대 상이 됨
public class OrderRepositoryV5 {

    private final TraceTemplate traceTemplate;
    public OrderRepositoryV5(LogTrace trace) {
        this.traceTemplate = new TraceTemplate(trace);
    }
    public void save(String item){
        traceTemplate.execute("OrderRepository.save()",()->{
            if(item.equals("ex")){
                throw new IllegalStateException("예외발생!");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
