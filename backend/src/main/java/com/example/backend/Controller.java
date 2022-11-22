package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class Controller {
    @GetMapping
    public String getNotiz() {
        return "das ist die erste Notiz in deinem Tagebuch.";
    }

    @GetMapping("second")
    public String getNotizOne() {
        return "das ist die zweite Notiz.";
    }

}
