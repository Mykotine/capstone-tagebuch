package com.example.backend.sevice;

import com.example.backend.domain.Note;
import com.example.backend.repos.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Long updateNoteById(Integer id, Note note) {
        Iterable<Note> notes = noteRepository.findAll();
        for(Note newNote : notes){
            if(note.getId().equals(id)){
                noteRepository.save(newNote);
                return note.getId();
            }
        }
        throw new NoSuchElementException("No notes was found with such id");
    }

    public boolean isNoteExisting(Integer id) {
        return noteRepository.existsById(id.longValue());
    }


    public void deleteNote(Integer id) {
        noteRepository.deleteById(id.longValue());
    }
}
