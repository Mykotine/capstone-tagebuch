package com.example.backend.repos;

import com.example.backend.note.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {

    List<Note> findByTag(String tag);
}
