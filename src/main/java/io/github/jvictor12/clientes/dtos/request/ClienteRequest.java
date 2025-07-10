package io.github.jvictor12.clientes.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest extends AbstractRequest implements Serializable {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String cpf;

}
