package com.example.estudio.repositorio;

import com.example.estudio.entidad.Contador;
import org.springframework.data.repository.CrudRepository;

// Que es un repositorio??

// Un repositorio es el intermediario entre tu código y la base de datos
// Tú le dices: "guarda este contador" o "búscame el contador que se llama visitas".
// Y él se encarga de traducirlo a SQL y ejecutarlo en la BD. Tú no escribes SQL, él lo hace solo.

public interface RepoContador extends CrudRepository<Contador, Long> {
    // CrudRepository<Contador, Long>
    // 1. Trabaja con la Tabla contador
    // 2. La clave primaria (de Contador) es de tipo Long



    Contador findByNombre(String nombre);
    // Spring lee el nombre del métdo y genera el siguiente SQL:
    //      SELECT * FROM CONTADOR WHERE NOMBRE = ?
}




// Con esta linea ya podemos: (gracias al extends)

//save(contador) → INSERT o UPDATE
//findById(id) → SELECT por ID
//findAll() → SELECT todos
//delete(contador) → DELETE