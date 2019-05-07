package org.goaler.eurekaclientdemo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.goaler.eurekaclientdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HystrixService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 注解 @HystrixCommand 开启断路器
     * fallbackMethod
     *      服务降级。执行错误时会调用
     * ignoreExceptions
     *      不触发服务降级的异常。 除了HystrixBadRequestException之外，
     *      其他异常均会被 Hystrix 认为命令执行失败并触发服务降级的处理逻辑。
     * groupKey
     *      命令组。会根据组来组织和统计命令的告警、仪表盘等信息
     * commandKey
     *      命令名称
     * threadPoolKey
     *      指定命令线程池。没有设置则同组内的命令共用线程池。
     *
     * 注解 @CacheResult 开启缓存。
     * cacheKeyMethod
     *      指定生成key的方法。如果不指定则更加方法参数生成key，
     *      如果使用 @CacheKey  在方法参数上，则只使用有注解的参数生成key
     */
    @HystrixCommand(
            fallbackMethod = "defaultUser",
            groupKey = "testGroup",
            commandKey = "testCmd",
            threadPoolKey = "testThreadPool"
    )
    @CacheResult(
            cacheKeyMethod = "userCacheKey"
    )
    public User getUser(int id){
        return restTemplate.getForObject("http://EUREKA-CLIENT/eureka-client/demo/hello", User.class);
    }

    /**
     * 服务降级方法
     *
     * throwable 捕捉到的异常。方法没有这个参数就抛弃捕捉到的异常。
     * @return
     */
    private User defaultUser(int id, Throwable throwable){
        return new User();
    }

    private String userCacheKey(Integer id){
        return id.toString();
    }
}
