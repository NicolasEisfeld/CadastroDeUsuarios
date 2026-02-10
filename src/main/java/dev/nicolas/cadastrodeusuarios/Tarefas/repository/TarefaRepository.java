package dev.nicolas.cadastrodeusuarios.Tarefas.repository;

import dev.nicolas.cadastrodeusuarios.Tarefas.model.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaModel, Long > {

    List<TarefaModel> findByUsuarioId(Long usuarioId);
}
