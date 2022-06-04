package br.com.example.api.service;

import br.com.example.api.domain.User;

public interface UserService {
    User findById(Integer id);
}
