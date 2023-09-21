package com.nagarro.notes.dto;

import com.nagarro.notes.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotesDto {
    private int noteId;
    private String content;
    private Date addedDate;
    private User user;
}
