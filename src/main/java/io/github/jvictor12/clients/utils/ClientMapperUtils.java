package io.github.jvictor12.clients.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jvictor12.clients.dtos.request.ClientRequest;
import io.github.jvictor12.clients.dtos.response.ClientResponse;
import io.github.jvictor12.clients.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientMapperUtils {

    private final ObjectMapper mapper;

    public Client toModel (ClientRequest clientRequest) { return mapper.convertValue(clientRequest, Client.class); }

    public ClientResponse fromModel (Client client) { return mapper.convertValue(client, ClientResponse.class); }
}
