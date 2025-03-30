package br.com.codart.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI(ServletContext servletContext) {
        final String securitySchemeName = "Authorization";
        return new OpenAPI()
                .addServersItem(new Server().url(servletContext.getContextPath()))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")))
                .info(
                        new Info()
                                .title("Medical Schedule API")
                                .version("1.0.0")
                                .description(
                                        "This project is the Medical Schedule API, a project that aims to provide a base for new projects in Tenda")
                                .termsOfService("http://swagger.io/terms/")
                                .contact(new Contact().name("Tenda").url("https://www.tenda.com.br/"))
                                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
