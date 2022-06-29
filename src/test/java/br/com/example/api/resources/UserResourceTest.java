package br.com.example.api.resources;

import br.com.example.api.domain.User;
import br.com.example.api.domain.dto.UserDTO;
import br.com.example.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserResourceTest {

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserService service;

    @Mock
    private ModelMapper mapper;


    private static final Integer ID = 1;
    private static final String NAME = "Valdir";
    private static final String EMAIL = "valdir@mail.com";
    private static final String PASSWORD = "123";
    private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final String EMAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";

    private UserDTO userDTO;
    private User user;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindUserByIdThenReturnAnUserInstance() {
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
    }

    @Test
    void whenFindAllUsersThenReturnAListOfUsersDTO() {
        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(0).getClass());

    }

    @Test
    void whenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(user);
        ResponseEntity<UserDTO> response = resource.create(userDTO);
        assertNotNull(response);
        assertNotNull(response.getHeaders().get("Location"));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(service.update(any())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.update(ID, userDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());


    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}