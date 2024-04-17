package hello.proxy.pureproxy.decorator.code;

import hello.proxy.pureproxy.proxy.code.Subject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {
    private Component component;
    private String cacheValue;
    public MessageDecorator(Component component){
        this.component = component;
    }
    @Override
    public String operation() {
    log.info("MessageDecorator 실행");
    String result = component.operation();
    String decoResult = "***** "+result + " *****";
    return decoResult;
    }
}
