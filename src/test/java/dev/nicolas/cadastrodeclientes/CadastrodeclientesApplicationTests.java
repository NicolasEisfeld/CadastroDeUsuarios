package dev.nicolas.cadastrodeclientes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import dev.nicolas.cadastrodeusuarios.Tarefas.Model.TarefaModel;

@SpringBootTest(classes = dev.nicolas.cadastrodeusuarios.CadastrodeusuariosApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class CadastrodeusuariosApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTarefaSucess() {
		// Criar uma tarefa simples - o serviço criará um usuário padrão automaticamente
		var tarefa = new TarefaModel("Teste de Tarefa", "Descrição do Teste 1", false, 1);

		webTestClient
				.post()
				.uri("/api/tarefas")
				.bodyValue(tarefa)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.size()").isEqualTo(1)
				.jsonPath("$[0].titulo").isEqualTo(tarefa.getTitulo())
				.jsonPath("$[0].descricao").isEqualTo(tarefa.getDescricao())
				.jsonPath("$[0].realizado").isEqualTo(false)
				.jsonPath("$[0].prioridade").isEqualTo(tarefa.getPrioridade());

	}

	@Test
	void testCreateTarefasFailure() {

		var tarefaInvalida = new TarefaModel(null, "Descrição válida", false, 1);

		webTestClient
				.post()
				.uri("/api/tarefas")
				.bodyValue(tarefaInvalida)
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody()
				.jsonPath("$.status").isEqualTo(400)
				.jsonPath("$.error").isEqualTo("Erro de negócio")
				.jsonPath("$.message").exists();
	}

}
