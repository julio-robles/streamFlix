package com.example.streamflix.repository;

import com.example.streamflix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String nombreUsuario);
    Optional<User> findByEmail(String correoElectronico);
    boolean existsByUsername(String nombreUsuario);
    boolean existsByEmail(String correoElectronico);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    int updatePassword(Long id, String password);
}