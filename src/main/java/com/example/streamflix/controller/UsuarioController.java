package com.example.streamflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @PutMapping("/{id}/cambiar-contrasena")
  public ResponseEntity<?> cambiarContrasena(@PathVariable Long id, @RequestBody String nuevaContrasena) {
    boolean resultado = usuarioService.cambiarContrasena(id, nuevaContrasena);
    if (resultado) {
      return ResponseEntity.ok("Contraseña actualizada correctamente.");
    } else {
      return ResponseEntity.status(404).body("Usuario no encontrado.");
    }
  }
}