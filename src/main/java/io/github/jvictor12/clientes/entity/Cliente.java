package io.github.jvictor12.clientes.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.jvictor12.clientes.enums.ClienteType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "tb_clientes")
public class Cliente extends AbstractEntity {

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 14)
    @JsonFormat(pattern = "000.000.000-00")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 10)
    private ClienteType tipo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){ this.dataCadastro = LocalDate.now(); }

    @PreUpdate
    public void preUpdate() { this.dataCadastro = LocalDate.now(); }
}
