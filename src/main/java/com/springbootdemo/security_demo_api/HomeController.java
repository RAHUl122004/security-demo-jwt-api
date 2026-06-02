package com.springbootdemo.security_demo_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/public")
    public String home(){
        return "Welcome to string demo api";
    }
    @GetMapping("/private")
public String privatePage() {
    return "This is a private page";
}

    @GetMapping("/admin")
public String adminPage() {
    return "This is a admin page";
}
}
