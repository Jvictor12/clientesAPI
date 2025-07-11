package io.github.jvictor12.clientes.repository;

import io.github.jvictor12.clientes.entity.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Map;
import java.util.UUID;

@NoRepositoryBean
public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {

    /**
     * Realiza uma busca paginada utilizando filtros dinâmicos.
     *
     * @param filters mapa de filtros para a busca
     * @param pageable informações de paginação
     * @return página de entidades encontradas
     */
    default Page<T> search(Map<?, ?> filters, Pageable pageable) {
        return findAll(buildSpecification(filters), pageable);
    }

    /**
     * Constrói uma especificação JPA a partir dos filtros informados.
     *
     * @param filters mapa de filtros
     * @return especificação JPA para consulta
     */
    Specification<T> buildSpecification(Map<?, ?> filters);
}
