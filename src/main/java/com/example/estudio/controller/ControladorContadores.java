package com.example.estudio.controlador;

import com.example.estudio.entidad.Contador;
import com.example.estudio.entidad.Operacion;
import com.example.estudio.repositorio.RepoContador;
import com.example.estudio.servicio.ServicioContadores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


// ESTO ES CASI IGUAL A ControladorRest, solo que ControladorRest utilizada ese HashMap para guardar
// las cosas, este nuevo Controlador usa RepoContador para guardar en la base de datos en vez de
// en un HashMap.


// ------ ESTO ES PERSISTENCIA REAL!!! -------



@RestController
public class ControladorContadores_comunicacionRepositorio {

    @Autowired RepoContador repoContador;
    // Crea un nuevo objeto del tipo RepoContador, este se comunicará con la base de datos él solito



    @PostMapping("/api/contadores")
    @ResponseStatus(HttpStatus.CREATED)
    public Contador crea(@RequestBody Contador contadorNuevo) {
        // Recibe los datos del contador y los convierte en una entidad Contador que lo llama contadorNuevo
        return repoContador.save(contadorNuevo);
        // Guarda el contadorNuevo en RepoContador, que se comunica con la base de datos y carga el nuevo objeto a la misma

    }

    @GetMapping("/api/contadores/{nombre}")
    public Contador lee(@PathVariable String nombre) {
        return repoContador.findByNombre(nombre);
        // Devuelve el contador con el nombre que has puesto en el String nombre
    }




    @PutMapping("/api/contadores/{nombre}/incremento/{incremento}")
    public Contador incrementa(@PathVariable String nombre, @PathVariable Long incremento) {
        Contador contador = repoContador.findByNombre(nombre);
        // Busca en la BD el contador con ese nombre
        contador.valor += incremento;
        // Le suma el incremento al valor actual
        return repoContador.save(contador);
        // Guarda el contador actualizado en la BD y lo devuelve
    }



    @DeleteMapping("/api/contadores/{nombre}")
    public void borra(@PathVariable String nombre) {
        Contador contador = repoContador.findByNombre(nombre);
        // Busca el contador con ese nombre
        repoContador.delete(contador);
        // Lo borra de la BD
    }



}
















//--------------------------------------------------------------------------------------------------------------





// Este controlador recibe information de HTTP y se la pasa al Servicio, el que entonces se comunica con la BD

// Desde aquí solo se va a recibir información y pasarla al Servicio para que luego con sus metodos como
// servicioContadores.crea() o .lee, se comunique con la base de datos


@RestController
public class ControladorContadores_comunicacionServicio {

    @Autowired ServicioContadores servicioContadores;
    // Crea crea automáticamente el ServicioContadores y lo mete en esta variable



    @PostMapping("/api/contadores")
    @ResponseStatus(HttpStatus.CREATED)
    public Contador crea(@RequestHeader("Authorization") String credenciales, @RequestBody Contador contadorNuevo) {
        // Recibe las credenciales del header de la petición y el contador nuevo a crear
        return servicioContadores.crea(contadorNuevo, servicioContadores.autentica(credenciales));
        // Autentica al usuario con sus credenciales y crea el contador en la BD usando el métdo .crea()

    }

    @GetMapping("/api/contadores/{nombre}")
    public Contador lee(@RequestHeader("Authorization") String credenciales, @PathVariable String nombre) {
        // Recibe las credenciales del header y el nombre del contador a buscar
        return servicioContadores.lee(nombre, servicioContadores.autentica(credenciales));
        // Autentica al usuario y devuelve el contador con ese nombre

    }



    @PutMapping("/api/contadores/{nombre}/incremento/{incremento}")
    public Contador incrementa(@RequestHeader("Authorization") String credenciales, @PathVariable String nombre, @PathVariable Long incremento) {
        // Recibe las credenciales, el nombre del contador y cuánto incrementar
        Contador contador = servicioContadores.lee(nombre, servicioContadores.autentica(credenciales));
        // Autentica al usuario y busca el contador con ese nombre
        return servicioContadores.incrementa(contador, incremento, servicioContadores.autentica(credenciales));
        // Incrementa el contador y lo guarda en la BD

    }

    @DeleteMapping("/api/contadores/{nombre}")
    public void borra(@RequestHeader("Authorization") String credenciales, @PathVariable String nombre) {
        // Recibe las credenciales y el nombre del contador a borrar
        Contador contador = servicioContadores.lee(nombre, servicioContadores.autentica(credenciales));
        // Autentica al usuario y busca el contador con ese nombre
        servicioContadores.borra(contador, servicioContadores.autentica(credenciales));
        // Borra el contador de la BD

    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    // Cuando se produzca esta excepción (intentar crear un contador con nombre repetido), ejecuta este metdo
    @ResponseStatus(HttpStatus.CONFLICT)
    // Devuelve un error 409 CONFLICT
    public void contadorDuplicado() {}
    // No devuelve nada, solo el código de error








    //Esto se comunica con la ultima cosa añadida en ServicioContadores
    @GetMapping("/api/contadores/{nombre}/operaciones")
    public Iterable<Operacion> operaciones(@RequestHeader("Authorization") String credenciales, @PathVariable String nombre) {
        // Recibe las credenciales y el nombre del contador
        Contador contador = servicioContadores.lee(nombre, servicioContadores.autentica(credenciales));
        // Autentica al usuario y busca el contador con ese nombre
        return servicioContadores.operaciones(contador);
        // Devuelve todas las operaciones que se ha hecho sobre un contador en concreto
    }


}





