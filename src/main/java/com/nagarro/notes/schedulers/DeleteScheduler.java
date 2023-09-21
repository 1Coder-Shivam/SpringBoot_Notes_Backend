package com.nagarro.notes.schedulers;

import com.nagarro.notes.entity.Notes;
import com.nagarro.notes.entity.User;
import com.nagarro.notes.repository.NotesRepo;
import com.nagarro.notes.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.plaf.synth.Region;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeleteScheduler {
    @Autowired
    RegistrationRepository userRepository;

    @Autowired
    NotesRepo noteRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void scheduleTask()
    {
        List<User> users = userRepository.findAll();
        int limit = 10;

        for (User user : users) {
            List<Notes> notes = user.getNotes();
//            System.out.println(notes);
            if (notes.size() > limit) {
                List<Notes> notesToDelete = notes.stream()
                        .sorted((n1, n2) -> n2.getAddedDate().compareTo(n1.getAddedDate()))
                        .skip(limit)
                        .collect(Collectors.toList());

                noteRepository.deleteAllInBatch(notesToDelete);;
            }
        }
        System.out.println("Records Deleted");
    }
}
