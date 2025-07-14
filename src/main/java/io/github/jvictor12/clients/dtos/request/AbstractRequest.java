package io.github.jvictor12.clients.dtos.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractRequest {

    private UUID id;
}
