package spring.SpringLogTrace_practice.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.SpringLogTrace_practice.trace.TraceStatus;
import spring.SpringLogTrace_practice.trace.hellotrace.HelloTraceV1;

@Repository //ComponentScan의 대상이 됨
@RequiredArgsConstructor
public class OrderRepositoryV1 {

private final HelloTraceV1 trace;

    public void save(String item){
        TraceStatus status = trace.begin("OrderRepositoryV1.save()");
        try{
            if(item.equals("ex")){
                throw new IllegalStateException("예외발생");
            }
            sleep(1000);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status,e);
            throw e;
        }
    }
    public void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
