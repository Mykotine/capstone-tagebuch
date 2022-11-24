package com.example.backend.note;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")

public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping("myNotes")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Note addNote(@Valid @RequestBody Note newNote) {
    return noteService.saveNote(newNote);
    }

    @PutMapping
    public ResponseEntity<Note> updateNote(@PathVariable String id, @Valid @RequestBody Note note){
        boolean noteExists = noteService.checkIfExist(id);

        Note toUpDate = note.withId(id);
        Note updatedNote = noteService.saveNote(toUpDate);

        return noteExists ?
                new ResponseEntity<>(updatedNote, HttpStatus.OK) :
                new ResponseEntity<>(updatedNote, HttpStatus.CREATED);

    }
}
