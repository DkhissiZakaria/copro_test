package com.app.copro.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @PostMapping("/syndic")
    public String testSyndic() {
        return "Test syndic endpoint works!";
    }
    
    @GetMapping("/health")
    public String health() {
        return "API is running";
    }
}