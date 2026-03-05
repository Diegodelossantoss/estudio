package com.example.estudio.entidad;

import jakarta.persistence.*;

// Una tabla que se llama Usuario y que tiene 3 Columnas
// id, email y credenciales, siendo ID la primary KEY y email y credenciales no pudiendo ser nulas,
// teniendo que ser email único (no puede haber dos entries con el mismo email)


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    public String email;

    @Column(nullable = false)
    public String credenciales;
}