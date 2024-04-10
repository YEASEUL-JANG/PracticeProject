package hello.proxy;

import hello.proxy.config.AppConfigV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(AppConfigV1.class) // 클래스를 스프링 빈으로 등록한다.
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class ProxyPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyPracticeApplication.class, args);
	}

}
