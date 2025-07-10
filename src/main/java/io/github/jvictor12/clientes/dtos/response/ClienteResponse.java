package io.github.jvictor12.clientes.dtos.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {

    private String nome;
    private LocalDate dataCadastro;
}
