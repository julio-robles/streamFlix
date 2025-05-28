package com.example.streamflix.service;

import com.example.streamflix.exception.IncorrectPasswordException;
import com.example.streamflix.model.Role;
import com.example.streamflix.model.User;
import com.example.streamflix.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRole(Role.USER);
        user.setStatus(User.Status.ACTIVE);
    }

    @Test
    void registerUser_success() {
        when(usuarioRepository.existsByNombreUsuario("testuser")).thenReturn(false);
        when(usuarioRepository.existsByCorreoElectronico("test@example.com")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encoded");
        when(usuarioRepository.save(any(User.class))).thenReturn(user);

        User result = userService.registerUser(user);

        assertThat(result).isNotNull();
        verify(usuarioRepository).save(any(User.class));
    }

    @Test
    void registerUser_usernameExists_throwsException() {
        when(usuarioRepository.existsByNombreUsuario("testuser")).thenReturn(true);

        assertThatThrownBy(() -> userService.registerUser(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("nombre de usuario ya está en uso");
    }

    @Test
    void authenticateUser_success() {
        when(usuarioRepository.findByNombreUsuario("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "password")).thenReturn(true);

        User result = userService.authenticateUser("testuser", "password");

        assertThat(result).isEqualTo(user);
    }

    @Test
    void authenticateUser_wrongPassword_throwsException() {
        when(usuarioRepository.findByNombreUsuario("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "password")).thenReturn(false);

        assertThatThrownBy(() -> userService.authenticateUser("testuser", "wrong"))
                .isInstanceOf(IncorrectPasswordException.class);
    }

    @Test
    void getUserById_found() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertThat(result).isEqualTo(user);
    }

    @Test
    void getUserById_notFound_throwsException() {
        when(usuarioRepository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(2L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void changePassword_success() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "password")).thenReturn(true);
        when(passwordEncoder.encode("newpass")).thenReturn("encodedNew");

        userService.changePassword(1L, "password", "newpass");

        verify(usuarioRepository).updatePassword(1L, "encodedNew");
    }

    @Test
    void changePassword_wrongOldPassword_throwsException() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "password")).thenReturn(false);

        assertThatThrownBy(() -> userService.changePassword(1L, "wrong", "newpass"))
                .isInstanceOf(IncorrectPasswordException.class);
    }
}