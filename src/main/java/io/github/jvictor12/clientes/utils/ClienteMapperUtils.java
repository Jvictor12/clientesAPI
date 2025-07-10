package io.github.jvictor12.clientes.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jvictor12.clientes.dtos.request.ClienteRequest;
import io.github.jvictor12.clientes.dtos.response.ClienteResponse;
import io.github.jvictor12.clientes.entity.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteMapperUtils {

    private final ObjectMapper mapper;

    public Cliente toModel (ClienteRequest clienteRequest) { return mapper.convertValue(clienteRequest, Cliente.class); }

    public ClienteResponse fromModel (Cliente cliente) { return mapper.convertValue(cliente, ClienteResponse.class); }
}
