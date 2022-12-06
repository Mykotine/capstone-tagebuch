package com.example.backend.note;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;




@Data
public class Note{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private String tag;

}
