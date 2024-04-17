package hello.proxy.pureproxy;

import hello.proxy.pureproxy.code.CacheProxy;
import hello.proxy.pureproxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest(){
        RealSubject subject = new RealSubject();
        ProxyPatternClient proxyPatternClient = new ProxyPatternClient(subject);
        proxyPatternClient.excute();
        proxyPatternClient.excute();
        proxyPatternClient.excute();
    }

    @Test
    void cacheProxyTest(){
        RealSubject subject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(subject);
        ProxyPatternClient proxyPatternClient = new ProxyPatternClient(cacheProxy);
        proxyPatternClient.excute();
        proxyPatternClient.excute();
        proxyPatternClient.excute();
    }
}
