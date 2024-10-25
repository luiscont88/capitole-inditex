package com.capitole.store.infrastructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${capitole.store.openapi.dev-url}")
    private String devUrl;

    /**
     * Configures the OpenAPI definition for the Capitole Store API.
     *
     * @return OpenAPI object with custom settings
     */
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("luis.contreras.20@gmail.com");
        contact.setName("Inditex");
        contact.setUrl("https://www.capitole-consulting.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Capitole Store API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage Capitole Store.")
                .termsOfService("https://www.capitole-consulting.com/terms")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }

}