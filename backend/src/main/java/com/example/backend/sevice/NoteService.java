package com.example.backend.sevice;

import com.example.backend.domain.Note;
import com.example.backend.repos.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Iterable<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note saveNote(Note note){
        return noteRepository.save(note);
    }

    public List<Note> getFindByTag(String tag){
        return noteRepository.getFindByTag(tag);
    }
}
