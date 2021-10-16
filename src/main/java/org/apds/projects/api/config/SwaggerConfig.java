package org.apds.projects.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
            .select()
            .paths(PathSelectors.any())
            .build()
            .directModelSubstitute(LocalDate.class, String.class)
            .genericModelSubstitutes(Optional.class, Mono.class, Flux.class, ResponseEntity.class)
            .ignoredParameterTypes(ApiIgnore.class)
            ;
    }
}
