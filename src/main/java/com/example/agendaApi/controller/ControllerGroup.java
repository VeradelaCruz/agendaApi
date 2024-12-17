package com.example.agendaApi.controller;

import com.example.agendaApi.classes.GroupPhone;
import com.example.agendaApi.service.ServiceGroup;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("api/group")
public class ControllerGroup {
    @Autowired
    public ServiceGroup serviceGroup;

    @PostMapping("/addGroup")
    public ResponseEntity<?> addGroup(@RequestBody List<GroupPhone> groupPhone) {
        if (groupPhone.size() == 1) {
        GroupPhone savedGroup = serviceGroup.saveGroup(groupPhone.get(0));
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
        }else{
            List<GroupPhone> savedGroups= serviceGroup.saveGroups(groupPhone);
            return new ResponseEntity<>(savedGroups, HttpStatus.CREATED);
        }
    }

    @GetMapping("/showGroups")
    public List<GroupPhone> showGroups(){
        return serviceGroup.findAllGroups();
    }

    @GetMapping("/showGroups/byID/{idGroup}")
    public ResponseEntity<?> showGroupByName(@PathVariable Long idGroup) {
        try {
            Optional<GroupPhone> foundedGroup = serviceGroup.findGroupById(idGroup);
            if (foundedGroup.isPresent()) {
                return ResponseEntity.ok(foundedGroup.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Grupo no encontrado con ID: " + idGroup);
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Grupo no encontrado con ID: " + idGroup);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    @GetMapping("/showGroups/byName/{nameGroup}")
    public ResponseEntity<?> showGroupByName(@PathVariable String nameGroup){
        try{
            Optional<GroupPhone> foundedGroupName = serviceGroup.findGroupByName(nameGroup);
            if (foundedGroupName.isPresent()){
                return ResponseEntity.ok(foundedGroupName.get());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo no encontrado con nombre: " + nameGroup);
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}
