package com.example.estudio.servicio;

import com.example.estudio.entidad.*;
import com.example.estudio.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;

@Service
// Le dice a Spring que esta clase es un Servicio

//(Hasta ahora el controlador hacía tdo directamente con el repositorio (ControladorContadores). Pero cuando una
//      operación afecta a varias entidades a la vez (contador + usuario + operacion), meterlo
//      tdo en el controlador se lía mucho.
//Por eso existe el servicio — es una capa intermedia.

// El controlador solo recibe la petición HTTP y llama al servicio. El servicio es quien hace toda la lógica de BD.





public class ServicioContadores {

    @Autowired RepoUsuario repoUsuario;
    @Autowired RepoContador repoContador;
    @Autowired RepoOperacion repoOperacion;
    // Crea  los 3 repositorios y los mete en estas 3 variables, para que puedas usarlos después dentro del servicio.



    public Usuario autentica(String credenciales) {
        Usuario usuario = repoUsuario.findByCredenciales(credenciales);
        // ¿Existe alguien con estas credenciales?" y guarda el resultado en la variable usuario.
        if (usuario == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        // Si no encontró a nadie (null), lanza un error 401 UNAUTHORIZED.
        return usuario;
        // Si sí encontró a alguien, lo devuelve.
    }




    public Contador crea(Contador contadorNuevo, Usuario usuario) {
        // Recibe que Contador crea y que Usuario lo crea
        Contador contador = repoContador.save(contadorNuevo);
        // Guarda el Contador contador en la tabla repoContador
        Operacion operacion = new Operacion();
        // Crea un objeto Operacion vacio para registrar lo que acaba de pasar
        operacion.contador = contador;
        operacion.usuario = usuario;
        operacion.tipo = "creación";
        operacion.fecha = LocalDateTime.now();
        // Rellena la operación recien creada
        repoOperacion.save(operacion);
        // Guarda la operación en el repoOperacion
        return contador;
        // Devuelve contador creado
    }




    public Contador lee(String nombre, Usuario usuario) {
        // Recibe el nombre del contador a buscar y el usuario que lo está leyendo
        Contador contador = repoContador.findByNombre(nombre);
        // Busca en la tabla Contador el contador con ese nombre
        if (contador == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // Si no existe, lanza un error 404
        Operacion operacion = new Operacion();
        // Crea un objeto Operacion vacío para registrar lo que acaba de pasar
        operacion.contador = contador;
        operacion.usuario = usuario;
        operacion.tipo = "lectura";
        operacion.fecha = LocalDateTime.now();
        // Rellena la operación recién creada
        repoOperacion.save(operacion);
        // Guarda la operación en repoOperacion
        return contador;
        // Devuelve el contador encontrado
    }




    public Contador incrementa(Contador contador, Long incremento, Usuario usuario) {
        // Recibe el contador a incrementar, cuánto incrementar y el usuario que lo hace
        contador.valor += incremento;
        // Suma el incremento al valor actual del contador
        repoContador.save(contador);
        // Guarda el contador actualizado en la tabla CONTADOR
        Operacion operacion = new Operacion();
        // Crea un objeto Operacion vacío para registrar lo que acaba de pasar
        operacion.contador = contador;
        operacion.usuario = usuario;
        operacion.tipo = "incremento";
        operacion.fecha = LocalDateTime.now();
        // Rellena la operación recién creada
        repoOperacion.save(operacion);
        // Guarda la operación en repoOperacion
        return contador;
        // Devuelve el contador actualizado
    }




    public void borra(Contador contador, Usuario usuario) {
        // Recibe el contador a borrar y el usuario que lo borra
        Operacion operacion = new Operacion();
        // Crea un objeto Operacion vacío para registrar lo que acaba de pasar
        operacion.contador = contador;
        operacion.usuario = usuario;
        operacion.tipo = "borrado";
        operacion.fecha = LocalDateTime.now();
        // Rellena la operación recién creada
        repoOperacion.save(operacion);
        // Guarda la operación en repoOperacion ANTES de borrar el contador
        repoContador.delete(contador);
        // Borra el contador de la tabla CONTADOR
    }










    public Iterable<Operacion> operaciones(Contador contador) {
        // Recibe un contador
        return repoOperacion.findByContador(contador);
        // Devuelve todas las operaciones que se han hecho sobre ese contador
    }



}