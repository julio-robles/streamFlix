package com.example.streamflix.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nombre_usuario", nullable = false, unique = true, length = 50)
  private String nombreUsuario;

  @Column(name = "correo_electronico", nullable = false, unique = true, length = 100)
  private String correoElectronico;

  @Column(name = "contrasena_encriptada", nullable = false, length = 255)
  private String contrasenaEncriptada;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private Rol rol;

  @Column(name = "fecha_registro", nullable = false)
  private LocalDateTime fechaRegistro;

  @Column(nullable = false)
  private boolean activo;
}