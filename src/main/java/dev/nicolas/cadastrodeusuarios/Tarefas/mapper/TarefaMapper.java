package dev.nicolas.cadastrodeusuarios.Tarefas.mapper;



import dev.nicolas.cadastrodeusuarios.Tarefas.dto.TarefaDTO;
import dev.nicolas.cadastrodeusuarios.Tarefas.model.TarefaModel;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {
    

        public TarefaModel map(TarefaDTO TarefaDTO) {
            TarefaModel TarefaModel = new TarefaModel();
            TarefaModel.setId(TarefaDTO.getId());
            TarefaModel.setDescricao(TarefaDTO.getDescricao());
            TarefaModel.setPrioridade(TarefaDTO.getPrioridade());
            TarefaModel.setUsuario(TarefaDTO.getUsuario());

            return TarefaModel;
        }

        public TarefaDTO map(TarefaModel TarefaModel) {
            TarefaDTO TarefaDTO = new TarefaDTO();
            TarefaDTO.setId(TarefaModel.getId());
            TarefaDTO.setDescricao(TarefaModel.getDescricao());
            TarefaDTO.setPrioridade(TarefaModel.getPrioridade());
            TarefaDTO.setUsuario(TarefaModel.getUsuario());

            return TarefaDTO;
        }


}
