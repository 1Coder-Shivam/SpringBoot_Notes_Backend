package com.nagarro.notes.repository;

import com.nagarro.notes.entity.Notes;
import com.nagarro.notes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotesRepo extends JpaRepository<Notes, Integer> {
    List<Notes> findByUserId(int id);
    List<Notes> findTop10ByUserIdOrderByAddedDateDesc(int userId);

    List<Notes> findByAddedDateBefore(LocalDateTime dateTime);

    @Transactional
    @Modifying
    @Query("DELETE FROM Notes n WHERE n.noteId = :noteId")
    void deleteByNoteId(@Param("noteId") int noteId);


}
