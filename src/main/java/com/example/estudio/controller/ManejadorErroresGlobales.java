package com.example.estudio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;


// Una clase separada que actúa como red de seguridad global para toda la aplicación.
// El bloque anterior solo captura errores de validación. Pero van a haber otros errores,
//      como intentar crear un contador que ya existe (409 CONFLICT) o pedir uno que no existe (404 NOT FOUND).

@ControllerAdvice
// Esto le dice a Spring que esta clase vigila TODOS los controladores, no uno en específico
public class ManejadorErroresGlobales {
    @ResponseBody
    // Hace que el código devuelva JSON
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity errorLanzado(ResponseStatusException ex) {

        return new ResponseEntity<>(ex.getStatusCode());
        // Devuelve una respuesta vacía pero con el código correcto (.getStatusCode());

    }
}

