package spring.SpringLogTrace_practice.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * Context: 변하지 않는 로직을 가진 템플릿 역할
 * 이 Context는 내부에 Strategy 필드를 가지고 있고 해당 구현체를 주입하면 된다.
 * 구현부분이 인터페이스에만 의존되어 있음.
 *
 * v2 : 전략을 파라미터로 전달받는 방식
 */
@Slf4j
public class ContextV2 {
    public void execute(Strategy strategy){
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        strategy.call(); //위임
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
