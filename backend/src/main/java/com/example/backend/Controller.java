package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class Controller {

    @GetMapping
    String getNote(){
        return "this is my first Note!";
    }


    @PostMapping("addNote")
    public String addNote(@RequestBody String note) {
        return note;
    }



}
