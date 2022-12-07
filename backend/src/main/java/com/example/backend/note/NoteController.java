package com.example.backend.note;

import com.example.backend.repos.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/")
    public String home(Map<String, Object>  model) {
        return "home";
    }
    String noteStr = "notes";


    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Note> notes = noteRepository.findAll();
    model.put(noteStr, notes);
    return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){
        Note note = new Note(text, tag);
        noteRepository.save(note);
        Iterable<Note> notes = noteRepository.findAll();
        model.put(noteStr, notes);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Note> notes;

        if (filter !=null && !filter.isEmpty()){
            notes = noteRepository.findByTag(filter);
        } else {
            notes = noteRepository.findAll();
        }
        model.put(noteStr, notes);
        return "main";
    }

}

