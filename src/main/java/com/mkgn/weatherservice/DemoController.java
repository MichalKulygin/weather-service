package com.mkgn.weatherservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/user")
    String getUser() {
        return "Hello user";
    }

    @GetMapping("/admin")
    String getAdmin() {
        return "Hello admin";
    }
}
