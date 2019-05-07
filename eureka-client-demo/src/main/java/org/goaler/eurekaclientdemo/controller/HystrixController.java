package org.goaler.eurekaclientdemo.controller;

import org.goaler.eurekaclientdemo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hystrix")
public class HystrixController {

    @GetMapping("user")
    public User getUser(){
        User u = new User();
        u.setName("goaler");
        u.setSex("男");
        u.setAge((short) 20);
        u.setJob("马隆");
        return u;
    }
}
