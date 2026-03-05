package com.example.estudio.repositorio;

import com.example.estudio.entidad.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface RepoUsuario extends CrudRepository<Usuario, Long> {


    Usuario findByCredenciales(String credenciales);


}
