package br.com.example.api.resources.exceptions;

import br.com.example.api.service.exceptions.DataIntegratyViolationException;
import br.com.example.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenREturnAResponseEntity(){
        ResponseEntity<StandardError> response =
                exceptionHandler.objectNotFound(new ObjectNotFoundException("Objeto Não encontrado"),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertNotEquals("user/2", response.getBody().getPath());
//        assertEquals(LocalDateTime.now(), response.getBody().getTimestamp());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
    }

    @Test
    void whenDataIntegrityViolationThenReturnAResponseEntity(){
        ResponseEntity<StandardError> response = exceptionHandler.dataIntegrityViolation(new DataIntegratyViolationException("E-mail já cadastrado"),
                new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotEquals("user/2", response.getBody().getPath());
//        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());

    }




}