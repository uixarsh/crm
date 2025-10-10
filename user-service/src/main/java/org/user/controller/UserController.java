package org.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.user.entity.User;
import org.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping
  public List<User> getUsers() { 
    return service.getAllUsers(); 
  }

  @GetMapping("/{id}")
  public User getById(@PathVariable Long id) { 
    return service.getUserById(id); 
  }

  @PostMapping
  public User create(@RequestBody User user_data) { 
    return service.saveUser(user_data); 
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) { 
    service.deleteUser(id); 
  }
}