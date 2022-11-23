package com.example.backend.note;

import javax.validation.constraints.NotNull;
import java.util.Date;

public record NewNoteData(
        @NotNull(message = "NoteNumber can´t be Null")
        int noteNumber,
        @NotNull
        Date totalNotes
) {
}
