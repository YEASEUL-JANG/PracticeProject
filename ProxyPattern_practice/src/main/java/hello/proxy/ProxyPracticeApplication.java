package hello.proxy;

import hello.proxy.config.AppConfigV1;
import hello.proxy.config.AppConfigV2;
import hello.proxy.config.v1_proxy.ConcreteProxyConfig;
import hello.proxy.config.v1_proxy.InterfaceProxyConfig;
import hello.proxy.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import hello.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import hello.proxy.config.v3_proxyFactory.ProxyFactoryConfigV1;
import hello.proxy.config.v3_proxyFactory.ProxyFactoryConfigV2;
import hello.proxy.config.v4_postprocessor.BeanPostProcessorConfig;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(BeanPostProcessorConfig.class) // 클래스를 스프링 빈으로 등록한다.(수동등록)
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //컴포넌트 스캔 대상폴더
public class ProxyPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyPracticeApplication.class, args);
	}

	@Bean
	public LogTrace logTrace(){
		return new ThreadLocalLogTrace(); //둥시성문제 없애기위해
	}
}
