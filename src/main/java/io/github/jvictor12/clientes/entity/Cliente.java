package io.github.jvictor12.clientes.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "tb_clientes")
public class Cliente extends AbstractEntity {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String cpf;

    @NotNull
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
