package com.samuel.market.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig {
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select() // Usamos select() para establecer lo que queremos que se exponga en la documentacaión
                .apis(RequestHandlerSelectors.basePackage("com.samuel.market.web.controller")) // en este caso solo queremos exponer los endpoints, estos se encuentran en el paquete de controladores
                .build();
    }
}
