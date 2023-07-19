package com.pachico.arithmetic.shared;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${franjagonca.openapi.local-url}")
    private String localUrl;

    @Value("${franjagonca.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl(localUrl);
        localServer.setDescription("Server URL in local environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("frgonzalezcaceres@gmail.com");
        contact.setName("Francisco Gonzalez");
        contact.setUrl("https://github.com/franja506");

        License mitLicense = new License().name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Arithmetic API")
                .version("1.0")
                .contact(contact)
                .description("This API retrieve multiple operations  ")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(localServer, prodServer));
    }
}
