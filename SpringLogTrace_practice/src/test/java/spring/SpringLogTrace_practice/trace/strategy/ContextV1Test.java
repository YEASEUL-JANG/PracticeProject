package spring.SpringLogTrace_practice.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.SpringLogTrace_practice.trace.strategy.code.ContextV1;
import spring.SpringLogTrace_practice.trace.strategy.code.Strategy;
import spring.SpringLogTrace_practice.trace.strategy.code.StrategyLogic1;
import spring.SpringLogTrace_practice.trace.strategy.code.StrategyLogic2;
import spring.SpringLogTrace_practice.trace.template.code.AbstractTemplate;
import spring.SpringLogTrace_practice.trace.template.code.SubClassLogic1;
import spring.SpringLogTrace_practice.trace.template.code.SubClassLogic2;

@Slf4j
public class ContextV1Test {
    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis(); //비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
    private void logic2() {
        long startTime = System.currentTimeMillis(); //비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    /**
     * 전략패턴 이용
     */
    @Test
    void strategyV1(){
        //전략을 만들고
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        //템플릿에 주입한다
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        //전략을 만들고
        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        //템플릿에 주입한다
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }
    /**
     *  전략패턴 - 익명 내부클래스 이용
     */
    @Test
    void strategyV2(){
        //전략을 만들고
        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };
        //템플릿에 주입한다.
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        //전략을 만들고
        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };
        //템플릿에 주입한다.
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }


    /**
     *  전략패턴 - 더 간단히 사용하기 (람다사용)
     *  -> 단 람다사용은 인터페이스에 구현할 메서드가 하나만 있어야 함.
     */
    @Test
    void strategyV3(){

        ContextV1 contextV1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(()-> log.info("비지니스 로직2 실행"));
        contextV2.execute();
    }
}
