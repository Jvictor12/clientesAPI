package io.github.jvictor12.clients.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.jvictor12.clients.enums.ClientType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "tb_clients")
public class Client extends AbstractEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "login", nullable = false, length = 30)
    private String login;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 10)
    private ClientType type;

    @Column(name = "RegistrationDate", length = 10)
    private LocalDate registrationDate;

    @PrePersist
    public void prePersist(){ this.registrationDate = LocalDate.now(); }

    @PreUpdate
    public void preUpdate() { this.registrationDate = LocalDate.now(); }
}
