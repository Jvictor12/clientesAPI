package io.github.jvictor12.clientes.dtos.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jvictor12.clientes.enums.ClienteType;
import io.github.jvictor12.clientes.utils.CpfSerializerUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @JsonSerialize(using = CpfSerializerUtils.class)
    @NotEmpty
    private String cpf;

    @NotNull
    private ClienteType tipo;

}
