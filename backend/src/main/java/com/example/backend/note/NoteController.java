package com.example.backend.note;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/")

public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @GetMapping("/home")
    public String getNewNote(@RequestParam(name="new_Note", required=false, defaultValue="your stories") String newNote, Model model) {
        model.addAttribute("new_Note", "this is your first note!");
        return "home";
    }


    @GetMapping
    public String main(Map<String, Object> model) {
    Iterable<Note> notes = noteService.getAllNotes();
    model.put("notes", notes);
    return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){
        Note note = new Note();
        noteService.saveNote(note);
        Iterable<Note> notes = noteService.getAllNotes();
        return "main";
    }

}

