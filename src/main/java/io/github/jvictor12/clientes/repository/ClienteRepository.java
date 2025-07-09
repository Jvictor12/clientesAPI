package io.github.jvictor12.clientes.repository;

import io.github.jvictor12.clientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Cliente findByCpf (String cpf);
}
