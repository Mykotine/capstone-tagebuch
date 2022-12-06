package com.example.backend.note;


import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Note{
    @Id
    private Integer id;

    private String text;
    private String tag;

}
