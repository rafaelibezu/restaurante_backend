package com.restaurante.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI sistemaRestauranteOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema Web de Gestión de Pedidos para Restaurante")
                        .version("1.0")
                        .description("API REST para la tesis: Implementación de un sistema web para optimizar la gestión de pedidos de un restaurante, Lima 2025.")
                        .contact(new Contact()
                                .name("Rafael Iberico y Edward Acosta")))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}