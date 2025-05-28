package com.example.streamflix.repository;

import com.example.streamflix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<User, Long> {
    Optional<User> findByNombreUsuario(String nombreUsuario);
    Optional<User> findByCorreoElectronico(String correoElectronico);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByCorreoElectronico(String correoElectronico);
}