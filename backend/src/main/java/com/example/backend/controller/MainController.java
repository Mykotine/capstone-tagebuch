package com.example.backend.controller;

import com.example.backend.domain.Note;
import com.example.backend.sevice.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {


    private final NoteService noteService;

    @GetMapping("/")
    public String home() {
        return "home";
    }
    String noteStr = "notes";


    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Note> notes = noteService.getAllNotes();
    model.put(noteStr, notes);
    return "main";
    }



    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){
        Note note = new Note(text, tag);
        noteService.saveNote(note);
        Iterable<Note> notes = noteService.getAllNotes();
        model.put(noteStr, notes);
        return "main";

    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Note> notes;

        if (filter !=null && !filter.isEmpty()){
            notes = noteService.getFindByTag(filter);
        } else {
            notes = noteService.getAllNotes();
        }
        model.put(noteStr, notes);
        return "main";
    }

}

