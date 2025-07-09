package io.github.jvictor12.clientes.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

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
    @CPF
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){ this.dataCadastro = LocalDate.now(); }

    @PreUpdate
    public void preUpdate() { this.dataCadastro = LocalDate.now(); }
}
