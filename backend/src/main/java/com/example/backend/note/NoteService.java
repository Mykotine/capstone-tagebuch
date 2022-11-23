package com.example.backend.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
