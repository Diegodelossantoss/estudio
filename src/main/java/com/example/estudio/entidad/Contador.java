package com.example.estudio.entidad;

import jakarta.persistence.*;


// Cada campo de la clase = una columna de la tabla.
// Cada objeto de esa clase = una fila de la tabla.



@Entity public class Contador {
    // @Entity -> Esta clase es una tabla". Sin esto, JPA la ignora completamente.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    // @Id → marca cuál es la clave primaria.
    // @GeneratedValue(strategy = GenerationType.IDENTITY) → el ID no lo pones tú a mano,
    //      la BD lo asigna sola y lo va incrementando (1, 2, 3...).

    @Column(nullable = false, unique = true) public String nombre;
    // @Column(nullable = false) → equivale a NOT NULL en SQL. El campo es obligatorio.
    // además con unique = true, dice que no puede haber dos filas con el mismo valor

    @Column(nullable = false) public Long valor;
}
