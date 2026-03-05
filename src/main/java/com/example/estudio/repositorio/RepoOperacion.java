package com.example.estudio.repositorio;

import com.example.estudio.entidad.Contador;
import com.example.estudio.entidad.Operacion;
import org.springframework.data.repository.CrudRepository;

public interface RepoOperacion extends CrudRepository<Operacion, Long> {
    Iterable<Operacion> findByContador(Contador contador);
}
