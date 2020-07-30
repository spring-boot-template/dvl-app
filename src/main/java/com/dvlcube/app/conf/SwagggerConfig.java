package com.dvlcube.app.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwagggerConfig {

    @Value("${spring.message}")
    private String message;

    @Bean
    public Docket api(BuildProperties buildProperties){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dvlcube.app.rest"))
                .paths(PathSelectors.ant("/**"))
                .build();

        Contact contact = new Contact("- Guilherme Costa","","tfguilherme.07@gmail.com");

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(buildProperties.getName())
                .description("Teste")
                .contact(contact)
                .version(buildProperties.getVersion().concat(" - ").concat(message))
                .build();

        docket.apiInfo(apiInfo);

        return docket;
    }
}
