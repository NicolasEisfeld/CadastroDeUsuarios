package dev.nicolas.cadastrodeusuarios.Tarefas.service;

import dev.nicolas.cadastrodeusuarios.Tarefas.model.TarefaModel;
import dev.nicolas.cadastrodeusuarios.Tarefas.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    private TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    // Adicionar Tarefa
    public TarefaModel adicionarTarefa(TarefaModel tarefaModel) {
        return tarefaRepository.save(tarefaModel);
    }

    // Listar Tarefas
    public List<TarefaModel> listarTarefas() {
        return tarefaRepository.findAll();
    }

    // Listar Tarefa Por ID
    public TarefaModel listarTarefaPorID(Long id) {
        return tarefaRepository.findById(id).orElse(null);
    }

    // Alterar Tarefa
    public TarefaModel alterarTarefa(Long id, TarefaModel tarefaModel) {
        if(tarefaRepository.existsById(id)) {
            tarefaModel.setId(id);
            return tarefaRepository.save(tarefaModel);
        } else {
            return null;
        }
    }

    // Deletar Tarefa Por ID
    public void deletarTarefaPorId(Long id) {
        tarefaRepository.deleteById(id);
    }
}
