package dev.nicolas.cadastrodeclientes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import dev.nicolas.cadastrodeusuarios.Tarefas.Model.TarefaModel;

@SpringBootTest
class CadastrodeusuariosApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTarefaSucess() {
		var tarefa = new TarefaModel("Teste de Tarefa", "Descrição do Teste 1", false, 1);
		
		webTestClient
			.post()
			.uri("/tarefas")
			.bodyValue(tarefa)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length").isEqualTo(1)
			.jsonPath("$[0].titulo").isEqualTo(tarefa.getTitulo())
			.jsonPath("$[0].descricao").isEqualTo(tarefa.getDescricao())
			.jsonPath("$[0].realizado").isEqualTo(tarefa.getPrioridade())
			.jsonPath("$[0].prioridade").isEqualTo(tarefa.getPrioridade());

	}

	@Test
	void testCreateTarefasFailure() {
	}

	@Test
	void testCreateTarefasSucess() {
	}

}
