package com.example.agendaApi.service;

import com.example.agendaApi.classes.GroupPhone;
import com.example.agendaApi.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceGroup {
    @Autowired
    public GroupRepository groupRepository;

    // Guardar un grupo, verificando si ya existe
    public GroupPhone saveGroup(GroupPhone groupPhone){
        // Buscar el grupo por nombre sin importar mayúsculas o minúsculas
        Optional<GroupPhone> existingGroup = groupRepository.findByNameGroupIgnoreCase(groupPhone.getNameGroup());

        if (existingGroup.isPresent()) {
            // Si el grupo ya existe, devolver el grupo existente
            return existingGroup.get();
        } else {
            // Si el grupo no existe, guardarlo
            return groupRepository.save(groupPhone);
        }
    }

    // Guardar varios grupos
    public List<GroupPhone> saveGroups(List<GroupPhone> groupPhones){
        return groupPhones.stream()
                .map(this::saveGroup) // Verifica cada grupo antes de guardarlo
                .collect(Collectors.toList());
    }

    //Ver todos los grupos
    public List<GroupPhone> findAllGroups(){
        return groupRepository.findAll();
    }

    //Ver un grupo por su id
    public Optional<GroupPhone> findGroupById(Long idGroup){
        return groupRepository.findById(idGroup);

    }

    //Ver grupo por su nombre
    public Optional<GroupPhone> findGroupByName(String nameGroup){
        return groupRepository.findByNameGroupIgnoreCase(nameGroup);
    }



}
