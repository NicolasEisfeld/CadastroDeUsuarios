package dev.nicolas.cadastrodeusuarios.Tarefas.mapper;

import dev.nicolas.cadastrodeusuarios.Tarefas.dto.TarefaDTO;
import dev.nicolas.cadastrodeusuarios.Tarefas.model.TarefaModel;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {

    public TarefaModel map(TarefaDTO tarefaDTO) {
        TarefaModel tarefaModel = new TarefaModel();
        tarefaModel.setId(tarefaDTO.getId());
        tarefaModel.setDescricao(tarefaDTO.getDescricao());
        tarefaModel.setPrioridade(tarefaDTO.getPrioridade());
        tarefaModel.setConcluida(tarefaDTO.isConcluida());
        tarefaModel.setStatus(tarefaDTO.getStatus());
        tarefaModel.setUsuario(tarefaDTO.getUsuario());
        tarefaModel.setUsuarioId(tarefaDTO.getUsuarioId());

        return tarefaModel;
    }

    public TarefaDTO map(TarefaModel tarefaModel) {
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setId(tarefaModel.getId());
        tarefaDTO.setDescricao(tarefaModel.getDescricao());
        tarefaDTO.setPrioridade(tarefaModel.getPrioridade());
        tarefaDTO.setConcluida(tarefaModel.isConcluida());
        tarefaDTO.setStatus(tarefaModel.getStatus());
        tarefaDTO.setUsuario(tarefaModel.getUsuario());
        tarefaDTO.setUsuarioId(tarefaModel.getUsuarioId());

        return tarefaDTO;
    }
}
