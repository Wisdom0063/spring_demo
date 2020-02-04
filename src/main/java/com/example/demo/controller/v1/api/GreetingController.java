package com.example.demo.controller.v1.api;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.Greeting;

@RestController
public class GreetingController {
    public static final String template = "Hello, %s!";
    public final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping("/greeting")
    public Greeting handleGreeting(){
        return new Greeting(counter.incrementAndGet(), "Hello Wisdom!");
    }
}
