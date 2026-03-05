package com.example.estudio.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


// CÓDIGO PARA REPASAR VALIDACIONES SENCILLAS


public record ModeloFormularioContacto(


        @NotBlank(message = "El mensaje no puede estar vacío")
        // @NotBlank valida que el campo no esté vacio
        // @NotBlank aplica al String de mensaje, por eso se pone arriba y luego separa con ,
        @Size(max = 20, message = "El mensaje no puede superar los 20 caracteres")
        // @Size hace que no se pueda superar 20 caracteres
        String mensaje,


        @Email(message = "El formato del email es incorrecto")
        // @Email valida que tenga formato email
        @NotBlank(message = "El email es obligatorio")
        String email
        // @Email y @NotBlank aplicana email, por eso se ponen arriba de la declaración


) {}
