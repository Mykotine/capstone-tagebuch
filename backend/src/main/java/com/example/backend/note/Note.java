package com.example.backend.note;

import lombok.With;


@With

public record Note(String id,
                   String text,
                   String date) {
}
