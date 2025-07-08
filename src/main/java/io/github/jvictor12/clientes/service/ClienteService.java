package io.github.jvictor12.clientes.service;

import io.github.jvictor12.clientes.entity.Cliente;
import io.github.jvictor12.clientes.exception.ObjectNotFoundException;
import io.github.jvictor12.clientes.exception.ValidationException;
import io.github.jvictor12.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow( ()-> new ObjectNotFoundException("Produto não encontrado"));
    }

    public List<Cliente> findAll() { return clienteRepository.findAll(); }

    public Cliente save (Cliente cliente) {

        if (cliente == null) {
            throw new ValidationException("Cliente inválido");
        }

        clienteRepository.save(cliente);

        return cliente;
    }

    public Cliente update (Cliente cliente) {
        if (cliente == null) {
            throw new ValidationException("Cliente inválido");
        }

        if(!clienteRepository.existsById(cliente.getId())){
            throw new ObjectNotFoundException("O cliente não foi encontrado");
        }

        clienteRepository.save(cliente);

        return cliente;
    }

    public void delete (Long id){
        if(id == null) {
            throw new ValidationException("Inválido");
        }
        if(!clienteRepository.existsById(id)){
            throw new ObjectNotFoundException("O cliente não foi encontrado");
        }

        clienteRepository.deleteById(id);
    }
}
