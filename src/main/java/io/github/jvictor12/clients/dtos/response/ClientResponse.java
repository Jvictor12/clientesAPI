package io.github.jvictor12.clients.dtos.response;

import io.github.jvictor12.clients.enums.ClientType;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse extends AbstractResponse implements Serializable {

    private String nome;

    private String cpf;

    private ClientType tipo;
}
