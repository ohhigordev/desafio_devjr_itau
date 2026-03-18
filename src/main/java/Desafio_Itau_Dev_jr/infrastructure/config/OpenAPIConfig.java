package Desafio_Itau_Dev_jr.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Transações e Estatísticas - Itaú")
                        .description("API desenvolvida para o desafio de programação do Itaú Unibanco.")
                        .version("v1.0"));
    }
}
