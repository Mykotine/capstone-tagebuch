package com.example.backend.controller;

import com.example.backend.domain.Note;
import com.example.backend.sevice.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final NoteService noteService;

    @Value("${upload.path}")
    private String uploadPath;


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
    public String add(@RequestParam String text,
                      @RequestParam String tag, Map<String, Object> model,
                      @RequestParam("file") MultipartFile file
                      ) throws NullPointerException, IOException {
        Note note = new Note(text, tag);
        if(file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();

            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File((uploadPath + resultFilename)));

            note.setFilename(resultFilename);
        }

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
        model.put("filter", "");
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

