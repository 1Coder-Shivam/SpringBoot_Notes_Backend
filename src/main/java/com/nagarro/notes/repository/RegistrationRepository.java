package com.nagarro.notes.repository;

import com.nagarro.notes.entity.Notes;
import com.nagarro.notes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<User,Integer> {

    User findByEmailId(String emailId);

}