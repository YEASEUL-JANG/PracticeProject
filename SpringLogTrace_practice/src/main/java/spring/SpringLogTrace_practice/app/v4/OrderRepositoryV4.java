package spring.SpringLogTrace_practice.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.SpringLogTrace_practice.trace.TraceId;
import spring.SpringLogTrace_practice.trace.logtrace.LogTrace;
import spring.SpringLogTrace_practice.trace.template.AbstractTemplate;

@Repository //ComponentScan의 대 상이 됨
@RequiredArgsConstructor
public class OrderRepositoryV4 {

private final LogTrace trace;

    public void save(String item){
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                if(item.equals("ex")){
                    throw new IllegalStateException("예외발생!");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
