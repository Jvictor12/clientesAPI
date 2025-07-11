package io.github.jvictor12.clientes.service;


import io.github.jvictor12.clientes.entity.AbstractEntity;
import io.github.jvictor12.clientes.exception.ObjectNotFoundException;
import io.github.jvictor12.clientes.exception.OperationFailureException;
import io.github.jvictor12.clientes.exception.ValidationException;
import io.github.jvictor12.clientes.repository.AbstractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Slf4j
public abstract class AbstractService<T extends AbstractEntity> {

    /**
     * Retorna o repositório JPA associado à entidade.
     *
     * @return JpaRepository para a entidade T.
     */
    protected abstract AbstractRepository<T> getRepository();

    /**
     * Constrói um objeto Pageable com base nos parâmetros fornecidos.
     *
     * @param page O número da página a ser retornada.
     * @param size O número de elementos por página.
     * @param sort A ordenação a ser aplicada.
     * @return Um objeto Pageable configurado.
     */
    protected Pageable buildPageable(Integer page, Integer size, Sort sort) {
        return (page != -1 && size != -1) ? PageRequest.of(page, size, sort) : PageRequest.of(0, Integer.MAX_VALUE, sort);
    }

    /**
     * Constrói um objeto Sort com base nos parâmetros fornecidos.
     *
     * @param sort      A propriedade pela qual a ordenação deve ser feita.
     * @param direction A direção da ordenação (ascendente ou descendente).
     * @return Um objeto Sort configurado.
     */
    protected Sort buildSort(String sort, String direction) {
        return Sort.by(Sort.Direction.fromString(direction), sort);
    }

    /**
     * Exclui uma entidade pelo seu ID.
     *
     * @param id O ID da entidade a ser excluída.
     * @throws ObjectNotFoundException   Se a entidade com o ID especificado não for encontrada.
     * @throws OperationFailureException Se ocorrer um erro ao tentar excluir a entidade.
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID id) {

        if (id != null && getRepository().existsById(id)) {
            try {
                getRepository().deleteById(id);
            } catch (Exception ex) {
                log.error("Error while deleting object: {}", ex.getMessage(), ex);
                throw new OperationFailureException(OperationFailureException.DEFAULT_MESSAGE);
            }
        } else {
            throw new ObjectNotFoundException("Objeto não encontrado!");
        }
    }

    /**
     * Retorna uma página de entidades convertidas em DTOs, com base nos parâmetros de paginação e ordenação.
     *
     * @param page      O número da página a ser retornada.
     * @param size      O número de elementos por página.
     * @param sort      A propriedade pela qual a ordenação deve ser feita.
     * @param direction A direção da ordenação (ascendente ou descendente).
     * @return Uma página de DTOs.
     */
    @Transactional(readOnly = true)
    public Page<T> findAll(Integer page, Integer size, String sort, String direction) {
        final var pageableSort = buildSort(sort, direction);
        final var pageable = buildPageable(page, size, pageableSort);
        return getRepository().findAll(pageable);
    }

    /**
     * Retorna um DTO correspondente à entidade com o ID especificado.
     *
     * @param id O ID da entidade a ser encontrada.
     * @return O DTO correspondente à entidade.
     * @throws ObjectNotFoundException Se a entidade com o ID especificado não for encontrada.
     */
    @Transactional(readOnly = true)
    public T findById(UUID id) {
        return getRepository().findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    /**
     * Salva uma entidade no banco de dados e retorna o DTO correspondente.
     *
     * @param entity A entidade a ser salva.
     * @return O DTO correspondente à entidade salva.
     * @throws OperationFailureException Se ocorrer um erro ao tentar salvar a entidade.
     */
    @Transactional(rollbackFor = Exception.class)
    public T save(T entity) {

        if (validate(entity)) {
            if (entity.getId() != null) {
                if (!getRepository().existsById(entity.getId())) {
                    throw new ObjectNotFoundException("Objeto não encontrado!");
                }
            }

            try {
                entity = getRepository().save(entity);
            } catch (Exception ex) {
                log.error("Error while saving object: {}", ex.getMessage(), ex);
                throw new OperationFailureException(OperationFailureException.DEFAULT_MESSAGE);
            }
        }

        return entity;
    }

    /**
     * Executa uma pesquisa baseada em filtros e retorna uma página de DTOs.
     *
     * @param filters   Map contendo os filtros da pesquisa.
     * @param page      Número da página a ser retornada.
     * @param size      Número de elementos por página.
     * @param sort      Propriedade pela qual a ordenação deve ser feita.
     * @param direction Direção da ordenação (ascendente ou descendente).
     * @return Uma página de DTOs.
     */
    @Transactional(readOnly = true)
    public Page<T> search(Map<?, ?> filters, Integer page, Integer size, String sort, String direction) {
        final var pageableSort = buildSort(sort, direction);
        final var pageable = buildPageable(page, size, pageableSort);
        return getRepository()
                .search(filters, pageable);
    }

    /**
     * Valida a entidade antes de ser salva. Este método deve ser sobrescrito
     * pelas subclasses para fornecer validações específicas.
     *
     * @param entity A entidade a ser validada.
     * @return true se a entidade for válida, false caso contrário.
     * @throws ValidationException Se a entidade não for válida.
     */
    @Transactional(readOnly = true)
    public boolean validate(T entity) {
        return true;
    }
}
