package io.github.jvictor12.clients;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ClientsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
