package com.nagarro.notes.service.impl;

import com.nagarro.notes.custom.exception.NoteCreationException;
import com.nagarro.notes.custom.exception.NoteDeletionException;
import com.nagarro.notes.custom.exception.NoteNotFoundException;
import com.nagarro.notes.dto.NotesDto;
import com.nagarro.notes.entity.Notes;
import com.nagarro.notes.mapper.NotesMapper;
import com.nagarro.notes.repository.NotesRepo;
import com.nagarro.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotesServiceImpl implements NotesService {
    @Autowired
    private NotesRepo notesRepository;

    @Autowired
    private NotesMapper notesMapper;

    @Override
    public NotesDto createNote(NotesDto noteDto) throws NoteCreationException {
        try {
            Notes note = notesMapper.dtoToNotes(noteDto);
            note.setAddedDate(new Date());
            Notes savedNote = notesRepository.save(note);
            return notesMapper.notesToDto(savedNote);
        } catch (Exception ex) {
            throw new NoteCreationException("Failed to create the note: " + ex.getMessage());
        }
    }

    @Override
    public void deleteNoteById(int noteId) throws NoteDeletionException {
        try {
            notesRepository.deleteById(noteId);
        } catch (Exception ex) {
            throw new NoteDeletionException("Failed to delete the note with ID " + noteId + ": " + ex.getMessage());
        }
    }

    @Override
    public List<NotesDto> getNotesById(int id) throws NoteNotFoundException {
        List<Notes> notes = notesRepository.findTop10ByUserIdOrderByAddedDateDesc(id);
        if (notes.isEmpty()) {
            throw new NoteNotFoundException("No notes found for user with ID " + id);
        }
        return notes.stream()
                .map(notesMapper::notesToDto)
                .collect(Collectors.toList());
    }
}
