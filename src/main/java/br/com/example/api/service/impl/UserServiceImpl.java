package br.com.example.api.service.impl;

import br.com.example.api.domain.User;
import br.com.example.api.domain.dto.UserDTO;
import br.com.example.api.repository.UserRepository;
import br.com.example.api.service.UserService;
import br.com.example.api.service.exceptions.DataIntegratyViolationException;
import br.com.example.api.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    final UserRepository repository;
    final ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        log.info("retorno da repository {}", obj);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Objecto não localizado."));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public User update(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void findByEmail(UserDTO userDTO){
        Optional<User> user = repository.findByEmail(userDTO.getEmail());

        if (user.isPresent() && !userDTO.getId().equals(user.get().getId()))
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");

    }

}


