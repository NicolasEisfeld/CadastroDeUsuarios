package dev.nicolas.cadastrodeusuarios.Tarefas.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.nicolas.cadastrodeusuarios.Tarefas.Model.TarefaModel;

public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {

    // Buscar tarefas por ID do usuário
    List<TarefaModel> findByUsuarioId(Long usuarioId);

    // Buscar tarefas por usuário e status de realização
    List<TarefaModel> findByUsuarioIdAndRealizado(Long usuarioId, boolean realizado);

    // Buscar tarefas por usuário e prioridade
    List<TarefaModel> findByUsuarioIdAndPrioridade(Long usuarioId, int prioridade);
}
