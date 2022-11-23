package com.example.backend.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Note addNewNote(NewNoteData newRequestData) {
        String newUuid = NoteUtil.generateUuid();

        int noteNumber = newRequestData.noteNumber();
        Date totalNotes = newRequestData.totalNotes();

        Note newNote = new Note(newUuid, noteNumber, totalNotes);
        noteRepository.save(newNote);

        return newNote;
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public Note checkIfExist(String noteId) {
        Optional<Note> noteToFind = noteRepository.findById(noteId);

        if(noteId.isEmpty()) {
            throw new NoSuchElementException("Note not Exist!");
        }
        return noteToFind.get();
     }


    public Note updateNote(String noteId, Note newData) {
        Note existNote = checkIfExist(noteId);

        Note updatedNote = new Note(existNote.id(), existNote.description(), newData.date());
        noteRepository.save(updatedNote);
        return updatedNote;
    }
}
