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
import org.user.entity.User;
import org.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @GetMapping
  public List<User> getUsers() { 
    return service.getAllUsers(); 
  }

  @GetMapping("/{id}")
  public User getById(@PathVariable Long id) { 
    return service.getUserById(id); 
  }

  // @PostMapping
  // public User create(@RequestBody User user_data) { 
  //   return service.saveUser(user_data); 
  // }

  @PostMapping
  public ResponseEntity<User> create(@Valid @RequestBody User user_data) {
  User savedUser = service.saveUser(user_data);
  return ResponseEntity.ok(savedUser);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) { 
    service.deleteUser(id); 
  }
}