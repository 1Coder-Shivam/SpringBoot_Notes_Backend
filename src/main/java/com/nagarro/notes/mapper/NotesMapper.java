package com.nagarro.notes.mapper;

import com.nagarro.notes.dto.NotesDto;
import com.nagarro.notes.entity.Notes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotesMapper {
    Notes dtoToNotes(NotesDto notesDto);
    NotesDto notesToDto(Notes notes);


}
