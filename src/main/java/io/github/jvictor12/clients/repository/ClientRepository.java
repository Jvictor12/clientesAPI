package io.github.jvictor12.clients.repository;

import io.github.jvictor12.clients.entity.Client;
import io.github.jvictor12.clients.enums.ClientType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public interface ClientRepository extends AbstractRepository<Client> {

    @Override
    default Specification<Client> buildSpecification(Map<?, ?> filters) {
        return (Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {

            final var predicates = new ArrayList<Predicate>();

            if (filters.containsKey("type")) {
                final var type = (ClientType) filters.get("type");
                predicates.add(criteriaBuilder.equal(root.get("type"), type));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
