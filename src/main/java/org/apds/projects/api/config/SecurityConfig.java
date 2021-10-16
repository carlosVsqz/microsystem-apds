package org.apds.projects.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import org.apds.projects.api.application.auth.service.support.AuthenticationJwtManager;
import org.apds.projects.api.application.auth.service.support.BearerSecurityContextRepository;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationJwtManager authenticationManager;

    @Autowired
    private BearerSecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return
            http
                .exceptionHandling()
                    .authenticationEntryPoint((exchange, e) -> Mono.fromCallable(() -> exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)).then())
                    .accessDeniedHandler((exchange, denied) -> Mono.fromCallable(() -> exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN)).then())
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .cors().disable()
                .csrf().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                    .pathMatchers("/api/ping/**").permitAll()
                    .pathMatchers("/api/test/**").permitAll()
                    .pathMatchers("/api/auth/login").permitAll()
                    .pathMatchers("/api/auth/signup").permitAll()
                    .pathMatchers(HttpMethod.OPTIONS).permitAll()
                    .pathMatchers("/api/**").authenticated()
                .anyExchange().permitAll()
                .and()
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
