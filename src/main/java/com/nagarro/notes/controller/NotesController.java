package com.nagarro.notes.controller;

import com.nagarro.notes.dto.NotesDto;
import com.nagarro.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    private NotesService noteService;

    @PostMapping
    public ResponseEntity<NotesDto> createNote(@RequestBody NotesDto noteDto) {
        NotesDto createdNote = noteService.createNote(noteDto);
        if (createdNote == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<NotesDto>> getNotesById(@PathVariable int id) {
        List<NotesDto> notes = noteService.getNotesById(id);
        System.out.println(id);
        if (notes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notes);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable int noteId) {
        try {
            System.out.println("controller delete: "+ noteId);
            noteService.deleteNoteById(noteId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
