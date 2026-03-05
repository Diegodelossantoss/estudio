// Maneja everything lo relacionado con la API. Devuelve JSON no HTML

// CÓDIGO DE MANEJO DE CONTADORES, POST, GET, PUT Y DELETE


package com.example.estudio.controller;
import com.example.estudio.exception.ExcepcionContadorIncorrecto;
import com.example.estudio.model.ModeloCampoIncorrecto;
import com.example.estudio.model.ModeloContador;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ControladorRest {

    private final Map<String, ModeloContador> contadores = new HashMap<>();
    // Crea un map con un String y un objeto de nuestra clase ModeloContador
    // contadores -> almacén temporal donde guardamos todos los contadores



    @PostMapping("/api/contadores")
    // EL POST SE HACE DESDE POSTMAN
    //
    //    {
    //       "nombre": "visitas",
    //       "valor": 0
    //   }
    //
    // Dice de dónde se reciben las peticiones, en este caso de xxxxxxx/api/contadores
    @ResponseStatus(HttpStatus.CREATED)
    // Dice a Spring que devuelva ese código "CREATED"
    public ModeloContador crea(@RequestBody ModeloContador contadorNuevo) {
        // Recibe los datos del contador y los convierte en un objeto ModeloContador que lo llama contadorNuevo
        contadores.put(contadorNuevo.nombre(), contadorNuevo);
        // Añade ese ModeloContador creado al HashMap de contadores con NombreContador - contadorNuevo
        return contadorNuevo;
    }



    @GetMapping("/api/contadores/{nombre}")
    // Lee el valor actual del contador con el {nombre} que has escrito en la URl
    // Si tienes un contador llamado Hola y otro Adiós, devuelve el valor del {nombre} que hayas escrito
    public ModeloContador contador(@PathVariable String nombre) {
        // El String nombre captura el nombre que has puesto en la URL
        return contadores.get(nombre);
        // Devuelvetodo el objeto ModeloContador que se guardó con la clave nombre de la URL
    }




    @PutMapping("/api/contadores/{nombre}/incremento/{incremento}")
    // Tiene dos huecos para variables, se ponen dos cosa
    // Asignas el valor {incremento} al contador {nombre}

    public ModeloContador incrementa(@PathVariable String nombre, @PathVariable Integer incremento) {
        ModeloContador contadorActual = contadores.get(nombre);
        // Se guarda en contadorActual el contador del que has hecho un PutMapping, para luego... v v v v v v v v
        ModeloContador contadorIncrementado = new ModeloContador(nombre, contadorActual.valor() + incremento);
        // Si haces put de {Hola}, 5, entonces {Hola} vale 5
        // Si luego haces put de {Hola}, 2, entonces {Hola} vale 5 + 2 = 7
        // Si quisieras reemplazar:
        // new ModeloContador(nombre, incremento)
        contadores.put(nombre, contadorIncrementado);
        // Actualiza el contador con el {nombre} que has puesto con el contador con el incremendo añadido
        return contadorIncrementado;
        // Devuelve el contador con el Put hecho e incrementado el valor
    }




    @DeleteMapping("/api/contadores/{nombre}")
    // Recoge el {nombre} del contador que queremos eliminar
    public ModeloContador elimina(@PathVariable String nombre) {
        return contadores.remove(nombre);
        // Elimina el contador con el {nombre} que has puesto
    }





    @ExceptionHandler(ExcepcionContadorIncorrecto.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ModeloCampoIncorrecto> contadorIncorrecto(ExcepcionContadorIncorrecto ex) {
        return ex.getErrores().stream().map(error -> new ModeloCampoIncorrecto(
                error.getDefaultMessage(), error.getField(), error.getRejectedValue()
        )).toList();
    }






}
