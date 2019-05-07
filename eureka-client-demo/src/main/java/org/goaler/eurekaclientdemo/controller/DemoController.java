package org.goaler.eurekaclientdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        System.out.println("hello world !" + Thread.currentThread().getName());
        Thread.sleep(1000000000);
        return "hello world !";
    }

    @GetMapping("/consumer")
    public String consumer(){
        ResponseEntity<String> respEntity = restTemplate.getForEntity("http://EUREKA-CLIENT/eureka-client/demo/hello", String.class);
        System.out.println("consumer success, return is " + respEntity.getBody());
        return "success";
    }
}