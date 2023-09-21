package com.nagarro.notes.service;

import com.nagarro.notes.custom.exception.NoteCreationException;
import com.nagarro.notes.custom.exception.NoteDeletionException;
import com.nagarro.notes.custom.exception.NoteNotFoundException;
import com.nagarro.notes.dto.NotesDto;

import java.util.List;

public interface NotesService {
    NotesDto createNote(NotesDto noteDto) throws NoteCreationException;
    void deleteNoteById(int noteId) throws NoteDeletionException;
    List<NotesDto> getNotesById(int id) throws NoteNotFoundException;
}
