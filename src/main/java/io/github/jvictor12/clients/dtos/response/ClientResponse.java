package io.github.jvictor12.clients.dtos.response;

import io.github.jvictor12.clients.enums.ClientType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ClientResponse extends AbstractResponse implements Serializable {

    private String name;

    private String cpf;

    private ClientType type;

    public ClientResponse(String name, String cpf, ClientType type) {
        this.name = name;
        this.cpf = cpf;
        this.type = type;
    }

    public ClientResponse() {
    }
}
