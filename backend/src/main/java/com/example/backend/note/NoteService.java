package com.example.backend.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Note addNewNote(Note note){
        String newUuid = NoteUtil.generateUuid();
        return new Note(newUuid, note.text(), note.date());
    }

    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public void deleteNote(String id) {
        noteRepository.deleteById(id);
    }

    public boolean checkIfExist(String id) {
        return noteRepository.existsById(id);

    }
}
