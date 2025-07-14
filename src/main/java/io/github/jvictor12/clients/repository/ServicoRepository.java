package io.github.jvictor12.clients.repository;

import io.github.jvictor12.clients.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Task, Long> {
}
