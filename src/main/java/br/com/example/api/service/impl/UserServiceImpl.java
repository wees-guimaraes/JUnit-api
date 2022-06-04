package br.com.example.api.service.impl;

import br.com.example.api.domain.User;
import br.com.example.api.repository.UserRepository;
import br.com.example.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    final UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        log.info("retorno da repository {}", obj);

        return obj.orElse(null);
    }
}
