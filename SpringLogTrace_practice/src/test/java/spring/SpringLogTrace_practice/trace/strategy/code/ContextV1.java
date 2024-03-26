package spring.SpringLogTrace_practice.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * Context: 변하지 않는 로직을 가진 템플릿 역할
 * 이 Context는 내부에 Strategy 필드를 가지고 있고 해당 구현체를 주입하면 된다.
 * 구현부분이 인터페이스에만 의존되어 있음.
 * v1 : 필드에 전략을 보관하는 방식
 */
@Slf4j
public class ContextV1 {
    private  Strategy strategy;

    // 생성자를 통해 전략을 주입받음.
    public ContextV1(Strategy strategy){
        this.strategy = strategy;
    }
    public void execute(){
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        strategy.call(); //위임
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
