package com.example.streamflix.service;

import com.example.streamflix.model.User;
import com.example.streamflix.model.Role;
import com.example.streamflix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // Registro de nuevos usuarios
  public User registerUser(User user) {
    if (usuarioRepository.existsByNombreUsuario(user.getUsername())) {
      throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
    }
    if (usuarioRepository.existsByCorreoElectronico(user.getEmail())) {
      throw new IllegalArgumentException("El correo electrónico ya está en uso.");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole(Role.USER);
    user.setStatus(User.Status.ACTIVE);
    return usuarioRepository.save(user);
  }

  // Autenticación de usuarios (login)
  public User authenticateUser(String username, String password) {
    User user = usuarioRepository.findByNombreUsuario(username)
                                 .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new IllegalArgumentException("Contraseña incorrecta.");
    }
    return user;
  }

  // Obtener perfil de usuario
  public Optional<User> getUserProfile(Long id) {
    return usuarioRepository.findById(id);
  }

  // Actualizar información de perfil
  public User updateUserProfile(Long id, User updatedUser) {
    User existingUser = usuarioRepository.findById(id)
                                         .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
    existingUser.setUsername(updatedUser.getUsername());
    existingUser.setEmail(updatedUser.getEmail());
    return usuarioRepository.save(existingUser);
  }

  // Cambiar contraseña
  public void changePassword(Long id, String oldPassword, String newPassword) {
    User user = usuarioRepository.findById(id)
                                 .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
      throw new IllegalArgumentException("La contraseña actual es incorrecta.");
    }
    user.setPassword(passwordEncoder.encode(newPassword));
    usuarioRepository.save(user);
  }

  // Gestión de roles (solo administradores)
  public User updateUserRole(Long id, Role newRole, User adminUser) {
    if (adminUser.getRole() != Role.ADMIN) {
      throw new SecurityException("Solo los administradores pueden gestionar roles.");
    }
    User user = usuarioRepository.findById(id)
                                 .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
    user.setRole(newRole);
    return usuarioRepository.save(user);
  }
}