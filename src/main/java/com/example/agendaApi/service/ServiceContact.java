package com.example.agendaApi.service;

import com.example.agendaApi.classes.Contact;
import com.example.agendaApi.classes.GroupPhone;
import com.example.agendaApi.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agendaApi.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceContact {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private GroupRepository groupRepository;

    //Buscamos todos los contactos:
    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    //Buscamos un contacto por id:
    public Optional<Contact> findContactById(Long idContact) {

        return contactRepository.findById(idContact);
    }

    //Buscamos un contacto por nombre:
    public List<Contact> findContactByName(String nameContact) {
        return contactRepository.findByNameContactIgnoreCase(nameContact);
    }

    //Buscamos un contacto por numero de telefono:
    public List<Contact> findContactByPhone(String phoneNumber) {
        // Agregar comodines para realizar una búsqueda parcial
        String searchPattern = "%" + phoneNumber + "%";
        return contactRepository.findByPhoneNumber(searchPattern);
    }


    //Guardar un contacto o contactos:
    public List<Contact> saveContacts(List<Contact> contacts) {
        for (Contact contact : contacts) {
            GroupPhone groupPhone = groupRepository.findByNameGroup(contact.getGroup().getNameGroup())
                    .orElseGet(() -> groupRepository.save(contact.getGroup()));
            contact.setGroup(groupPhone);
            contactRepository.save(contact);
        }
        return contacts;
    }


    //Eliminar un contacto:
    public boolean removeContact(Long idContact) {
        Optional<Contact> removingContact = findContactById(idContact);
        if (removingContact.isPresent()) {
            contactRepository.delete(removingContact.get());
            return true;
        } else {
            return false;
        }
    }

    //Modificar un contacto:
    public boolean modifyContact(Long idContact, Contact newContact) {
        Optional<Contact> existingContact = findContactById(idContact);
        if (existingContact.isPresent()) {
            Contact contact = existingContact.get();
            contact.setNameContact(newContact.getNameContact());
            contact.setAddress(newContact.getAddress());
            contact.setPhoneNumber(newContact.getPhoneNumber());
            contact.setNotes(newContact.getNotes());
            contact.setGroup(newContact.getGroup());
            contactRepository.save(contact); // Guardar los cambios en la base de datos
            return true; // Éxito
        }
        return false; // Contacto no encontrado
    }

}
