package dev.nicolas.cadastrodeusuarios.Tarefas.Service;

import java.util.List;
import java.util.Optional;
import dev.nicolas.cadastrodeusuarios.Tarefas.Repository.TarefaRepository;
import dev.nicolas.cadastrodeusuarios.Tarefas.Model.TarefaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    // Criar nova tarefa
    public List<TarefaModel> criarTarefa(TarefaModel tarefaModel) {
        tarefaRepository.save(tarefaModel);
        return listarTarefas(); // É utilizado o método listar nos outros métodos para a melhor exibição das mudanças
    }

    // Listar todas as tarefas
    @Transactional(readOnly = true)
    public List<TarefaModel> listarTarefas() {
        Sort sort = Sort.by("prioridade").descending().and
        (Sort.by("nome")).ascending();

        return tarefaRepository.findAll(sort);
    }

    // Buscar tarefa por ID
    @Transactional(readOnly = true)
    public Optional<TarefaModel> buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    // Atualizar tarefa
    public List<TarefaModel> atualizarTarefa(TarefaModel tarefaAtualizada) {
        tarefaRepository.save(tarefaAtualizada);
        return listarTarefas();
    }

    // Deletar tarefa
    public List<TarefaModel> deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
        return listarTarefas();
    }

    // Listar tarefas por usuário
    @Transactional(readOnly = true)
    public List<TarefaModel> listarTarefasPorUsuario(Long usuarioId) {
        return tarefaRepository.findByUsuarioId(usuarioId);
    }
}
