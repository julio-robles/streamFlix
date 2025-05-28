package com.example.streamflix.repository;

import com.example.streamflix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<User, Long> {
    Optional<User> findByNombreUsuario(String nombreUsuario);
    Optional<User> findByCorreoElectronico(String correoElectronico);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByCorreoElectronico(String correoElectronico);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    int updatePassword(Long id, String password);
}