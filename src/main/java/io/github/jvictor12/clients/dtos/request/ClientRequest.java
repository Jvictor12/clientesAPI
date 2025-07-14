package io.github.jvictor12.clients.dtos.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jvictor12.clients.enums.ClientType;
import io.github.jvictor12.clients.utils.CpfSerializerUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest extends AbstractRequest implements Serializable {

    @NotEmpty
    private String nome;

    @JsonSerialize(using = CpfSerializerUtils.class)
    @NotEmpty
    private String cpf;

    @NotNull
    private ClientType tipo;

}
