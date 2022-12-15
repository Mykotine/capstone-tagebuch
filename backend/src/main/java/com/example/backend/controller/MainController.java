package com.example.backend.controller;

import com.example.backend.domain.Note;
import com.example.backend.domain.User;
import com.example.backend.sevice.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
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
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Note> notes = noteService.getAllNotes();
        if (filter !=null && !filter.isEmpty()){
            notes = noteService.getFindByTag(filter);
        } else {
            notes = noteService.getAllNotes();
        }
    model.addAttribute(noteStr, notes);
        model.addAttribute("filter", filter);
    return "main";
    }



    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
            @RequestParam String text,
                      @RequestParam String tag, Map<String, Object> model,
                      @RequestParam("file") MultipartFile file
                      ) throws IOException {
        Note note = new Note(text, tag, user);
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
}

