package br.com.example.api.service;

import br.com.example.api.domain.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);

    List<User> findAll();
}
