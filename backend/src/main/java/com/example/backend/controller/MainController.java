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

    /*@PutMapping("/{id}")
    public Integer updateNoteById(@PathVariable Integer id, @RequestBody Note note){
        try{
            if(note.getId().equals(id)){
                return noteService.updateNoteById(id, note);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable Integer id) {
        if(noteService.isNoteExisting(id)){
            noteService.deleteNote(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No story with ID: " + id + " found" );
    }*/

}

