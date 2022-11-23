package com.example.backend.note;

import java.util.UUID;

public class NoteUtil {
    static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
