package com.example.estudio.model;

public record ModeloContador(String nombre, Long valor) { }

// Java genera automáticamente los getters, el constructor, etc. Es simplemente un atajo.
// Es una forma corta de crear una clase que solo guarda datos.
// Recuerdas la clase Alumno que hicimos antes con todos los getters y setters.
// Pues esto los genera directamente