package spring.SpringLogTrace_practice.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.SpringLogTrace_practice.trace.logtrace.LogTrace;
import spring.SpringLogTrace_practice.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {

        return new ThreadLocalLogTrace();
    }
}
