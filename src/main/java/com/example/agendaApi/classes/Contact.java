package com.example.agendaApi.classes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@Table(name = "contact")
@Data  // Lombok generará automáticamente getters, setters, toString, equals y hashCode
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private Long idContact;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    @Column(name = "name_contact", nullable = false, length = 100)
    private String nameContact;

    private String address;

    @Column(name = "phone_number", nullable = false, length = 15)
    @NotNull
    @Size(max = 15)
    private String phoneNumber;
    private String notes;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idGroup") //Conectamos la columna con la que se relacionaria el id en comun.
    private GroupPhone group;





}
