package com.example.estudio.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


// CÓDIGO PARA REPASAR VALIDACIONES SENCILLAS


public record ModeloFormularioContacto(


        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,
        // @NotBlank valida que el campo no esté vacio
        // @NotBlank aplica al String de mensaje, por eso se pone arriba y luego separa con ,



        @Email(message = "El formato del email es incorrecto")
        // @Email valida que tenga formato email
        @NotBlank(message = "El email es obligatorio")
        String email
        // @Email y @NotBlank aplicana email, por eso se ponen arriba de la declaración


) {}
