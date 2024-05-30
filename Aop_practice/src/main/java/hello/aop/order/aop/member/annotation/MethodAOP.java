package hello.aop.order.aop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//메서드에 붙이는 어노테이션
@Retention(RetentionPolicy.RUNTIME)//런타임 실행때까지 어노테이션이 살아있음
public @interface MethodAOP {
    String value();
}
