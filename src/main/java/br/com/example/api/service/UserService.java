package br.com.example.api.service;

import br.com.example.api.domain.User;
import br.com.example.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    User findById(Integer id);

    List<User> findAll();

    User create(UserDTO userDTO);
}
