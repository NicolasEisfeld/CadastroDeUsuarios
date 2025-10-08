package dev.nicolas.cadastrodeusuarios.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Cadastro de Usuários")
                        .description("API para gerenciamento de usuários e tarefas")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Nicolas Eisfeld")
                                .email("nicolas@example.com")));
    }
}
