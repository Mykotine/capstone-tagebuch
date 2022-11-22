package Note;

import java.util.Date;

public record Note(String id,
                   String description,
                   Date date) {
}
