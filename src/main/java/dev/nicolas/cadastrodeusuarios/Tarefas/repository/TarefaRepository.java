package dev.nicolas.cadastrodeusuarios.Tarefas.repository;

import dev.nicolas.cadastrodeusuarios.Tarefas.model.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<TarefaModel, Long > {



}
