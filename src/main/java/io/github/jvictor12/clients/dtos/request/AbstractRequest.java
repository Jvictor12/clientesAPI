package io.github.jvictor12.clients.dtos.request;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractRequest {

    private UUID id;
}
