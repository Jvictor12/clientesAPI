package io.github.jvictor12.clients.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.jvictor12.clients.enums.ClientType;
import io.github.jvictor12.clients.utils.CpfSerializerUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest extends AbstractRequest implements Serializable {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message= "Login cannot be empty")
    private String login;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @JsonSerialize(using = CpfSerializerUtils.class)
    @NotEmpty(message = "CPF cannot be empty")
    private String cpf;

    @NotNull(message = "ClientType cannot be null")
    private ClientType type;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationDate;
}
