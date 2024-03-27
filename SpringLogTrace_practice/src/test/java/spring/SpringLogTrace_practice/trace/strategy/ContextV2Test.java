package spring.SpringLogTrace_practice.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.SpringLogTrace_practice.trace.strategy.code.strategy.ContextV2;
import spring.SpringLogTrace_practice.trace.strategy.code.strategy.Strategy;
import spring.SpringLogTrace_practice.trace.strategy.code.strategy.StrategyLogic1;
import spring.SpringLogTrace_practice.trace.strategy.code.strategy.StrategyLogic2;

@Slf4j
public class ContextV2Test {

    /**
     * 전략패턴 이용 (execute() 시 파라미터값으로 전달)
     */
    @Test
    void strategyV1(){
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
    }
    /**
     *  전략패턴 - 익명 내부클래스 이용
     */
    @Test
    void strategyV2(){
        ContextV2 context1 = new ContextV2();
        context1.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });

        ContextV2 context2 = new ContextV2();
        context2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
    }


    /**
     *  전략패턴 - 더 간단히 사용하기 (람다사용)
     *  -> 단 람다사용은 인터페이스에 구현할 메서드가 하나만 있어야 함.
     */
    @Test
    void strategyV3(){

        ContextV2 context1 = new ContextV2();
        context1.execute(() -> log.info("비즈니스 로직1 실행"));
        context1.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
