package br.com.example.api.config;

import br.com.example.api.domain.User;
import br.com.example.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
@RequiredArgsConstructor
public class LocalConfig {

    final UserRepository repository;

    @Bean
    public void startDB(){
        User wesley = new User(null, "Wesley", "wees.guimaraes@gmail.com", "123");
        User maria = new User(null, "maria", "maria.guimaraes@gmail.com", "123");
        repository.saveAll(List.of(wesley, maria));
    }
}
