package com.nagarro.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noteId;

    @Size(max = 500, message = "Note cannot be more than 500 characters")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Date addedDate;
}
