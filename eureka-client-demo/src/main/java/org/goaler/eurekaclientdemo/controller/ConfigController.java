package org.goaler.eurekaclientdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${config.version}")
    private String configVersion;

    @GetMapping("/config-version")
    public String config(){
        return configVersion;
    }
}
