package io.github.jvictor12.clientes;

import io.github.jvictor12.clientes.entity.Cliente;
import io.github.jvictor12.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientesApplication {

    @Autowired
    ClienteRepository repository;

    @Bean
    public CommandLineRunner run(){
        return args -> {
            Cliente cliente = Cliente.builder().cpf("00000000000").nome("Ciclano").build();
            repository.save(cliente);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientesApplication.class, args);
    }
}
