package com.example.agendaApi.repository;

import com.example.agendaApi.classes.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long>{
    List<Contact> findByNameContact(String nameContact);

    @Query("SELECT c FROM Contact c WHERE LOWER(c.nameContact) = LOWER(:nameContact)")
    List<Contact> findByNameContactIgnoreCase(@Param("nameContact") String nameContact);

    Optional<Contact> findById(Long idContact);

    @Query("SELECT p FROM Contact p WHERE p.phoneNumber LIKE :phoneNumber")
    List<Contact> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}
