package com.example.backend.note;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/notes")

public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping("myNotes")
    public Note addNewNote(@Valid @RequestBody NewNoteData newNoteData) {
    return noteService.addNewNote(newNoteData);
    }

    @PutMapping
    public ResponseEntity<Note> updateNote(@PathVariable String noteId, @Valid @RequestBody Note newData){
        try{
            Note updatedNote = noteService.updateNote(noteId, newData);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(updatedNote);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID is not found!");
        }
    }





}
