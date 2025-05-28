package com.example.streamflix.controller;

import com.example.streamflix.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/refresh")
  public ResponseEntity<?> refreshToken(@RequestBody String refreshToken) {
    if (jwtUtil.validateToken(refreshToken)) {
      String username = jwtUtil.extractUsername(refreshToken);
      String newAccessToken = jwtUtil.generateAccessToken(username);
      return ResponseEntity.ok(newAccessToken);
    } else {
      return ResponseEntity.status(401).body("Invalid refresh token");
    }
  }
  @PostMapping("/logout")
  public ResponseEntity<?> logout() {
    // Invalidate the token (if using a token store, you would remove the token here)
    return ResponseEntity.ok("Logged out successfully");
  }
}