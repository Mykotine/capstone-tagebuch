package com.example.backend.note;

import java.util.Date;

public record Note(String id,
                   int description,
                   Date date) {
}
