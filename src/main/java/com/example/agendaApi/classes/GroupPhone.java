package com.example.agendaApi.classes;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "contact_group")
@Data // genera getters y setters automaticamente
public class GroupPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Se genera el id automaticamente incrementandolo
    private Long idGroup;
    @Column(name = "group_name")
    private String nameGroup;
    @OneToMany(mappedBy = "group")
    private List<Contact> contacts = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupPhone that = (GroupPhone) o;
        return Objects.equals(nameGroup, that.nameGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameGroup);
    }

}
