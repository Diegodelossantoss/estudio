package com.example.estudio.entidad;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
// Esto es una tabla
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    // Esto hace que el id de la operación sea la PRIMARY KEY

    @ManyToOne
    public Usuario usuario;
    // "Esta operación la hizo un usuario"
    // Muchas operaciones pueden hacerse por solo un Usuario, pero cada operación la hizo SOLO UN usuario
    // eg. Usuario1 puede hacer operaciones 1,2,3,... pero una operación solo puede hacerse por un usuario a la vez
    //              MANY Operacion's MUST BELONG TO 1 Usuario
    // Esto es un atributo de la clase Operacion
    // Que crea un objeto de la clase Usuario anteriormente creada.
    // Este objeto de la clase Usuario va a tener todos los atributos de la clase Usuario
    // Pero estarán asociados con los KEYs

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // Así cuando borras un contador, todas sus operaciones se borran en cascada.
    public Contador contador;
    // Muchas operaciones pueden referirse al mismo contador, pero cada operación solo afecta a un contador.

    @Column(nullable = false)
        public String tipo;
    // Atributo de la clase Operacion, un string llamado "tipo"

    @Column(nullable = false)
        public LocalDateTime fecha;
    // Atributo de la clase Operacion, un LocalDateTime llamado "fecha"
}