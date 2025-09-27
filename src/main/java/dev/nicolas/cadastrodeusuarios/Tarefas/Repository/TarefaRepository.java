package dev.nicolas.cadastrodeusuarios.Tarefas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.nicolas.cadastrodeusuarios.Tarefas.Model.TarefaModel;

public interface TarefaRepository extends JpaRepository<TarefaModel, Long >{

}
