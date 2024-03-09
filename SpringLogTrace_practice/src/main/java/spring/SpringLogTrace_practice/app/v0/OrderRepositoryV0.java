package spring.SpringLogTrace_practice.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static java.lang.Thread.sleep;

@Repository //ComponentScan의 대상이 됨
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String item){
        if(item.equals("ex")){
            throw new IllegalStateException("예외발생");
        }
        sleep(1000);
    }
    public void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
