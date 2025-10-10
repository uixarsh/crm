package org.user.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.user.entity.User;
import org.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  
  @Autowired
  private UserRepository repository;

  public List<User> getAllUsers() { 
    return repository.findAll(); 
  }

  public User saveUser(User user_data) { 
    return repository.save(user_data); 
  }

  public void deleteUser(Long id) { 
    repository.deleteById(id); 
  }

  public User getUserById(Long id) {
    return repository.findById(id).orElse(null);
  }
}