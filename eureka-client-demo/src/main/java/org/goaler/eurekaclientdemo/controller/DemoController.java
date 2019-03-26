package org.goaler.eurekaclientdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(){
        System.out.println("hello world !");
        return "hello world !";
    }

    @GetMapping("/consumer")
    public String consumer(){
        ResponseEntity<String> respEntity = restTemplate.getForEntity("http://EUREKA-CLIENT/eureka-client/demo/hello", String.class);
        System.out.println("consumer success, return is " + respEntity.getBody());
        return "success";
    }

    @Value("${config.version}")
    private String configVersion;

    @GetMapping("/config-version")
    public String config(){
        return configVersion;
    }
}