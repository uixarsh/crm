package org.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.user.dto.UserResponseDTO;
import org.user.entity.User;
import org.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  // Get all users 
  @GetMapping
  public List<UserResponseDTO> getUsers() {
      return service.getAllUsers();
  }

  // Get one user by ID 
  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
      UserResponseDTO user = service.getUserById(id);
      if (user == null) {
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(user);
  }

  // Create new user
  @PostMapping
  public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody User userData) {
      User savedUser = service.saveUser(userData);
      UserResponseDTO response = UserResponseDTO.builder()
              .id(savedUser.getId())
              .username(savedUser.getUsername())
              .email(savedUser.getEmail())
              .role(savedUser.getRole())
              .build();
      return ResponseEntity.ok(response);
  }

  // Delete user by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
      service.deleteUser(id);
      return ResponseEntity.noContent().build();
  }
}
