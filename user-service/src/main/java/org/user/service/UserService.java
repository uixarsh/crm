package org.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.user.dto.UserResponseDTO;
import org.user.entity.User;
import org.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public List<UserResponseDTO> getAllUsers() {
    return repository.findAll()
          .stream()
          .map(this::convertToDTO)
          .collect(Collectors.toList());
  }

  public UserResponseDTO getUserById(Long id) {
    return repository.findById(id)
          .map(this::convertToDTO)
          .orElse(null);
  }

  public User saveUser(User userData) {
      return repository.save(userData);
  }

  public void deleteUser(Long id) {
      repository.deleteById(id);
  }

  private UserResponseDTO convertToDTO(User user) {
    return UserResponseDTO.builder()
          .id(user.getId())
          .username(user.getUsername())
          .email(user.getEmail())
          .role(user.getRole())
          .build();
  }
}
