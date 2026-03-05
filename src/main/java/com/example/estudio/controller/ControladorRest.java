// Maneja everything lo relacionado con la API. Devuelve JSON no HTML

// CÓDIGO DE MANEJO DE CONTADORES, POST, GET, PUT Y DELETE


package com.example.estudio.controller;
import com.example.estudio.exception.ExcepcionContadorIncorrecto;
import com.example.estudio.model.ModeloCampoIncorrecto;
import com.example.estudio.model.ModeloContador;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ModeloContador crea(@Valid @RequestBody ModeloContador contadorNuevo, BindingResult bindingResult) {
        // Recibe los datos del contador y los convierte en un objeto ModeloContador que lo llama contadorNuevo
        // El @Valid se ha añadido luego, esto le dice a Spring que valide el objeto antes de procesarlo
        // BindingResult se ha añadido luego, para validación. Aquí el modelo guarda los errores si los hay

        if (bindingResult.hasErrors()) {
            throw new ExcepcionContadorIncorrecto(bindingResult);
        }
        // Si la validación ha fallado, es decir, coge el .getErrors(), crear un nuevo ExcepcionContadorIncorrecto
        // pasandole bindingResult como argumento

        if (contadores.containsKey(contadorNuevo.nombre())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        // Esto va a prevenir que se cree un contador con el mismo nombre que uno ya existente

        contadores.put(contadorNuevo.nombre(), contadorNuevo);
        // Añade ese ModeloContador creado al HashMap de contadores con NombreContador - contadorNuevo
        return contadorNuevo;
    }








    @GetMapping("/api/contadores/{nombre}")
    // Lee el valor actual del contador con el {nombre} que has escrito en la URl
    // Si tienes un contador llamado Hola y otro Adiós, devuelve el valor del {nombre} que hayas escrito
    public ModeloContador contador(@PathVariable String nombre) {
        // El String nombre captura el nombre que has puesto en la URL

        if (!contadores.containsKey(nombre)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        // Antes de devolver el contador, primero comprueba si existe. Si no existe, lanza error

        return contadores.get(nombre);
        // Devuelvetodo el objeto ModeloContador que se guardó con la clave nombre de la URL
    }






    @PutMapping("/api/contadores/{nombre}/incremento/{incremento}")
    // Tiene dos huecos para variables, se ponen dos cosa
    // Asignas el valor {incremento} al contador {nombre}

    public ModeloContador incrementa(@PathVariable String nombre, @PathVariable Integer incremento) {

        if (!contadores.containsKey(nombre)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        // Antes de devolver el contador, primero comprueba si existe. Si no existe, lanza error

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



    @PreAuthorize("hasRole('ADMIN')")
    // Esto se ha añadido después
    // "solo deja ejecutar este metdo a usuarios que tengan el rol ADMIN".
    // Así solo los admins pueden borrar contadores
    @DeleteMapping("/api/contadores/{nombre}")
    // Recoge el {nombre} del contador que queremos eliminar
    public ModeloContador elimina(@PathVariable String nombre) {
        return contadores.remove(nombre);
        // Elimina el contador con el {nombre} que has puesto
    }




    // ----------- ESTO ES PARA LAS EXCEPCIONES PERSONALIZADAS, -------------
    // ----------- FUNCIONA CON ExceptionContadorIncorrecto y ModeloCampoIncorrecto //

    @ExceptionHandler(ExcepcionContadorIncorrecto.class)
    // Esto le dice que cuando se lance ExcepcionContadorIncorrecto, se ejecute el siguiente código
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // Hace que la respuesta tenga código de error 400
    public List<ModeloCampoIncorrecto> contadorIncorrecto(ExcepcionContadorIncorrecto ex) {
        return ex.getErrores().stream().map(error -> new ModeloCampoIncorrecto(error.getDefaultMessage(),
                error.getField(), error.getRejectedValue())).toList();
        // Aquí hasta "new Modelo..." es siempre igual, aquí quieres devolver el mensaje, campo y codigo de error
        // "error" siempre es igual, dejarlo así. Solo cambia los de getField etc etc...
    }






}
