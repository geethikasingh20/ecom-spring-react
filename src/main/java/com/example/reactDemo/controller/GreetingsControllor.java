package com.example.reactDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:3000") // React dev server
public class GreetingsControllor {
	
	@GetMapping("/api/greeting")
    public String getGreeting(@RequestParam(defaultValue = "World") String name) {
        return "Hello, " + name + "!";
}
}
