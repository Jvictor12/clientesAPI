package io.github.jvictor12.clients.controller;

import io.github.jvictor12.clients.dtos.request.ClientRequest;
import io.github.jvictor12.clients.dtos.response.ClientResponse;
import io.github.jvictor12.clients.entity.Client;
import io.github.jvictor12.clients.enums.ClientType;
import io.github.jvictor12.clients.exception.ValidationException;
import io.github.jvictor12.clients.service.AbstractService;
import io.github.jvictor12.clients.service.ClientService;
import io.github.jvictor12.clients.utils.ClientMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController extends AbstractController<Client, ClientRequest, ClientResponse>{

    private final ClientService clientService;
    private final ClientMapperUtils clientMapperUtils;

    @Override
    protected AbstractService<Client> getService() { return clientService; }

    @Override
    protected Client toModel (ClientRequest clientRequest) { return clientMapperUtils.toModel(clientRequest); }

    @Override
    protected ClientResponse toResponseDTO(Client client) { return clientMapperUtils.fromModel(client); }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<ClientResponse> search(@RequestParam ClientType type,
                                        @RequestParam(required = false, defaultValue = "0") Integer page,
                                        @RequestParam(required = false, defaultValue = "10") Integer size,
                                        @RequestParam(required = false, defaultValue = "name") String sort,
                                        @RequestParam(required = false, defaultValue = "asc") String direction) {
        final var filters = new HashMap<String, Object>();

        if (type != null){
            filters.put("type", type);
        }

        if (filters.isEmpty()) {
            throw new ValidationException(("Parâmetros inválidos. Pelo menos um filtro deve ser fornecido"));
        }

        return getService().search(filters, page, size, sort, direction)
                .map(this::toResponseDTO);
    };
}
