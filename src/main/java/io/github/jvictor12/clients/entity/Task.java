package io.github.jvictor12.clients.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name= "tb_tasks")
public class Task extends AbstractEntity{

    @NotEmpty
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    private BigDecimal valor;
}
