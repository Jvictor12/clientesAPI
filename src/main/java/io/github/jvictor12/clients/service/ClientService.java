package io.github.jvictor12.clients.service;

import io.github.jvictor12.clients.entity.Client;
import io.github.jvictor12.clients.repository.AbstractRepository;
import io.github.jvictor12.clients.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService extends AbstractService<Client> {

    private final ClientRepository clientRepository;

    @Override
    protected AbstractRepository<Client> getRepository() { return clientRepository; }
}
