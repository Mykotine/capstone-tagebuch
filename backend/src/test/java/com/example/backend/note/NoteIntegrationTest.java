package com.example.backend.note;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc


class NoteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    void addNote() throws Exception {
        String requestNote = """
                {
                "id":"234",
                "text":"My first note.",
                "date":"24-11-2022"
                }
                """;

        String content = mockMvc.perform(MockMvcRequestBuilders.post("/api/myNotes")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestNote))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Note newNote = objectMapper.readValue(content, Note.class);

        assertFalse(newNote.id().isEmpty());
        assertEquals("My first note.", newNote.text());
        assertEquals("24-11-2022", newNote.date());
    }
}