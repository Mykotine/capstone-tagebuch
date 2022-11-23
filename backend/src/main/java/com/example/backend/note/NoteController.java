package com.example.backend.note;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping("myNotes")
    public Note addNewNote(@RequestBody NewNoteData requestNote) {
    return noteService.addNewNote(requestNote);
    }





}
