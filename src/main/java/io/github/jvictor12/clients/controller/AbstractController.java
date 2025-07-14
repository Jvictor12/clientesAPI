package io.github.jvictor12.clients.controller;

import io.github.jvictor12.clients.dtos.request.AbstractRequest;
import io.github.jvictor12.clients.dtos.response.AbstractResponse;
import io.github.jvictor12.clients.entity.AbstractEntity;
import io.github.jvictor12.clients.service.AbstractService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public abstract class AbstractController<T extends AbstractEntity,
        REQUEST_DTO extends AbstractRequest,
        RESPONSE_DTO extends AbstractResponse> {

    /**
     * Retorna o serviço associado à entidade.
     *
     * @return serviço da entidade
     */
    protected abstract AbstractService<T> getService();

    /**
     * Converte um DTO de requisição em uma entidade do modelo.
     *
     * @param requestDTO DTO de requisição
     * @return entidade do modelo
     */
    protected abstract T toModel(REQUEST_DTO requestDTO);

    /**
     * Converte uma entidade do modelo em um DTO de resposta.
     *
     * @param entity entidade do modelo
     * @return DTO de resposta
     */
    protected abstract RESPONSE_DTO toResponseDTO(T entity);

    /**
     * Exclui uma entidade pelo seu ID.
     *
     * @param id identificador único da entidade
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        getService().delete(id);
    }

    /**
     * Retorna uma página de entidades convertidas em DTOs de resposta.
     *
     * @param page      número da página
     * @param size      tamanho da página
     * @param sort      campo de ordenação
     * @param direction direção da ordenação (asc/desc)
     * @return página de DTOs de resposta
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<RESPONSE_DTO> findAll(@RequestParam(required = false, defaultValue = "0") Integer page,
                                      @RequestParam(required = false, defaultValue = "10") Integer size,
                                      @RequestParam(required = false, defaultValue = "createdDate") String sort,
                                      @RequestParam(required = false, defaultValue = "desc") String direction) {
        return getService().findAll(page, size, sort, direction).map(this::toResponseDTO);
    }

    /**
     * Busca uma entidade pelo seu ID e retorna o DTO de resposta correspondente.
     *
     * @param id identificador único da entidade
     * @return DTO de resposta
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RESPONSE_DTO findById(@PathVariable UUID id) {
        return toResponseDTO(getService().findById(id));
    }

    /**
     * Salva uma nova entidade a partir do DTO de requisição e retorna o DTO de resposta.
     *
     * @param requestDTO DTO de requisição
     * @return DTO de resposta
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RESPONSE_DTO save(@RequestBody @Valid REQUEST_DTO requestDTO) {
        final var model = getService().save(toModel(requestDTO));
        return toResponseDTO(model);
    }

    /**
     * Atualiza uma entidade existente com base no DTO de requisição e retorna o DTO de resposta atualizado.
     *
     * @param id         identificador único da entidade
     * @param requestDTO DTO de requisição
     * @return DTO de resposta atualizado
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RESPONSE_DTO update(@PathVariable UUID id, @RequestBody @Valid REQUEST_DTO requestDTO) {
        final var model = getService().save(toModel(requestDTO));
        return toResponseDTO(model);
    }
}