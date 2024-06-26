package com.example.nangmanmemo.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Value("${myapp.local-url}")
    private String localUrl;

//    @Value("${myapp.api-url}")
//    private String prodUrl;

    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = createOpenAPI();
        configureServers(openAPI);
        return openAPI;
    }

    private OpenAPI createOpenAPI() {
        Info info = new Info()
                .title("NangmanMemo API Document")
                .version("v0.0.1")
                .description("NangmanMemo 문서");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

    private void configureServers(OpenAPI openAPI) {
//        Server prodServer = new Server()
//                .description("Production Server")
//                .url(prodUrl);

        Server localServer = new Server()
                .description("Development Server")
                .url(localUrl);

        //openAPI.servers(Arrays.asList(prodServer, localServer));
        openAPI.servers(Arrays.asList(localServer));
    }
}

