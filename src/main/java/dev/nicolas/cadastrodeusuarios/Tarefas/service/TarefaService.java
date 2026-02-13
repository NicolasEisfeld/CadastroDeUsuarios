package dev.nicolas.cadastrodeusuarios.Tarefas.service;

import dev.nicolas.cadastrodeusuarios.Tarefas.dto.TarefaDTO;
import dev.nicolas.cadastrodeusuarios.Tarefas.mapper.TarefaMapper;
import dev.nicolas.cadastrodeusuarios.Tarefas.model.TarefaModel;
import dev.nicolas.cadastrodeusuarios.Tarefas.repository.TarefaRepository;
import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public TarefaService(TarefaRepository tarefaRepository, TarefaMapper tarefaMapper) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
    }

    // Adicionar Tarefa
    public TarefaDTO adicionarTarefa(TarefaDTO tarefaDTO) {
        TarefaModel tarefaModel = tarefaMapper.map(tarefaDTO);
        
        // Criar objeto UsuarioModel se tiver apenas o ID
        if (tarefaModel.getUsuario() == null && tarefaDTO.getUsuarioId() != null) {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setId(tarefaDTO.getUsuarioId());
            tarefaModel.setUsuario(usuario);
        }
        
        // Definir status padrão se não for informado
        if (tarefaModel.getStatus() == null || tarefaModel.getStatus().isEmpty()) {
            tarefaModel.setStatus("PENDENTE");
        }
        
        tarefaRepository.save(tarefaModel);
        return tarefaMapper.map(tarefaModel);
    }

    // Listar Tarefas
    public List<TarefaDTO> listarTarefas() {
        List<TarefaModel> tarefas = tarefaRepository.findAll();
        return tarefas.stream()
                .map(tarefaMapper::map)
                .toList();

    }

    // Listar Tarefas por Usuário
    public List<TarefaDTO> listarTarefasPorUsuario(Long usuarioId) {
        List<TarefaModel> tarefas = tarefaRepository.findByUsuarioId(usuarioId);
        return tarefas.stream()
                .map(tarefaMapper::map)
                .toList();
    }

    // Listar Tarefa Por ID
    public TarefaDTO listarTarefaPorID(Long id) {
        Optional<TarefaModel> tarefaModel = tarefaRepository.findById(id);
        return tarefaModel.map(tarefaMapper::map).orElse(null);
    }

    // Alterar Tarefa
    public TarefaDTO alterarTarefa(Long id, TarefaDTO tarefaDTO) {
        Optional<TarefaModel> tarefaExistente = tarefaRepository.findById(id);
        if(tarefaExistente.isPresent()) {
            TarefaModel tarefaAtualizada = tarefaMapper.map(tarefaDTO);
            tarefaAtualizada.setId(id);
            
            // Manter o usuário existente se não for informado
            if (tarefaAtualizada.getUsuario() == null && tarefaExistente.get().getUsuario() != null) {
                tarefaAtualizada.setUsuario(tarefaExistente.get().getUsuario());
            }
            
            tarefaRepository.save(tarefaAtualizada);
            return tarefaMapper.map(tarefaAtualizada);
        } else {
            return null;
        }
    }

    // Deletar Tarefa Por ID
    public void deletarTarefaPorId(Long id) {
        tarefaRepository.deleteById(id);
    }
}
