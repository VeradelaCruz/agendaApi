package com.example.agendaApi.controller;

import com.example.agendaApi.classes.Contact;
import com.example.agendaApi.service.ServiceContact;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ControllerContact {
    @Autowired
    public ServiceContact serviceContact;

    @GetMapping("/showContacts")  //Mostrar todas los contactos
    public List<Contact> getAllContacts() {

        return serviceContact.findAllContacts();
    }

    @GetMapping("/showContact/id/{idContact}")
    public ResponseEntity<?> getContactById(@PathVariable Long idContact) {
        try {
            Optional<Contact> contactRespuesta = serviceContact.findContactById(idContact);
            if (contactRespuesta.isPresent()) {
                return ResponseEntity.ok(contactRespuesta.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Contacto no encontrado con ID: " + idContact);
            }
        } catch (Exception e) {
            // Registra la excepción en los logs
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }


    @GetMapping("/showContact/name/{nameContact}")
    public ResponseEntity<List<Contact>> getContactByName(@PathVariable String nameContact) {
        List<Contact> contacts = serviceContact.findContactByName(nameContact);
        if (contacts.isEmpty()) {
            return ResponseEntity.notFound().build();  // Retorna 404 si no se encuentran contactos
        } else {
            return ResponseEntity.ok(contacts);  // Retorna la lista de contactos encontrados con 200 OK
        }
    }

    @GetMapping("/showContact/phone/{phoneNumber}")
    public ResponseEntity<Object> getContactByPhone(@PathVariable String phoneNumber) {
        List<Contact> phoneFunded = serviceContact.findContactByPhone(phoneNumber);
        try {
            if (phoneFunded.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(phoneFunded);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado: " + e.getMessage());
        }
    }


    @PostMapping("/addContacts")
    public ResponseEntity<List<Contact>> addContacts(@RequestBody @Valid List<Contact> contacts) {
        try {
            List<Contact> savedContacts = serviceContact.saveContacts(contacts);
            return ResponseEntity.ok(savedContacts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    

    @DeleteMapping("/deleteContact/{idContact}")
    public ResponseEntity<String> deleteContact(@PathVariable Long idContact){
        if (serviceContact.removeContact(idContact)){
            return  ResponseEntity.ok("El contacto con id: "+ idContact + " ha sido eliminado.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El contacto ingresado no existe.");
        }

    }

    @PutMapping("/modify/{idContact}")
    public ResponseEntity<String> changeContact(@PathVariable Long idContact, @RequestBody Contact newContact) {
        boolean isModified = serviceContact.modifyContact(idContact, newContact);

        if (isModified) {
            return ResponseEntity.ok("Los datos del contacto con id: " + idContact + " han sido actualizados.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El contacto con id: " + idContact + " no existe.");
        }
    }

}