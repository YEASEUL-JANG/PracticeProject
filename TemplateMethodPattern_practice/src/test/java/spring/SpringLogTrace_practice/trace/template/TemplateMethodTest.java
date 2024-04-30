package spring.SpringLogTrace_practice.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.SpringLogTrace_practice.trace.template.code.AbstractTemplate;
import spring.SpringLogTrace_practice.trace.template.code.SubClassLogic1;
import spring.SpringLogTrace_practice.trace.template.code.SubClassLogic2;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2(); }

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
        long endTime = System.currentTimeMillis(); long resultTime = endTime - startTime; log.info("resultTime={}", resultTime);
    }

    /**
     * 템플릿 메서드 패턴 적용
     */
    @Test
    void templateMethodV1(){
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }
    /**
     * 템플릿 메서드 패턴 - 익명 내부클래스 : Sub 클래스들을 따로 만들기 않고 바로 구현하는것.
     */
    @Test
    void templateMethodV2(){
        AbstractTemplate template1 = new AbstractTemplate() {
            //추상클래스를 상속받을 클래스를 여기에서 바로 만들 수 있음
            @Override
            protected void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        template1.execute();
        AbstractTemplate template2 = new AbstractTemplate() {
            //추상클래스를 상속받을 클래스를 여기에서 바로 만들 수 있음
            @Override
            protected void call() {
                log.info("비즈니스 로직 2 실행");
            }
        };
        template2.execute();
    }

}
