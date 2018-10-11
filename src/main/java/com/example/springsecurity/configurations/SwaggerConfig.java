package com.example.springsecurity.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

//https://github.com/springfox/springfox-demos
//http://springfox.github.io/springfox/docs/current/#spring-java-configuration
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() throws IOException, URISyntaxException {
        final List<ResponseMessage> globalResponses = Arrays.asList(
                new ResponseMessageBuilder()
                        .code(200)
                        .message("OK........aaaa")
                        .build(),
                new ResponseMessageBuilder()
                        .code(400)
                        .message("Bad Requestaaaaaaaaaaa")
                        .build(),
                new ResponseMessageBuilder()
                        .code(500)
                        .message("Internal Erroraaaaaaaaaaaaa")
                        .build());
        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, globalResponses)
                .globalResponseMessage(RequestMethod.POST, globalResponses)
                .globalResponseMessage(RequestMethod.DELETE, globalResponses)
                .globalResponseMessage(RequestMethod.PATCH, globalResponses)
                .select()
                .build();
    }
}