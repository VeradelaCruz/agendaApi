package com.example.agendaApi.repository;


import com.example.agendaApi.classes.GroupPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupPhone, Long> {

    @Query("SELECT g FROM GroupPhone g WHERE LOWER(g.nameGroup) = LOWER(:nameGroup)")
    Optional<GroupPhone> findByNameGroupIgnoreCase(@Param("nameGroup") String nameGroup);

    Optional<GroupPhone> findByNameGroup(String nameGroup);


}
