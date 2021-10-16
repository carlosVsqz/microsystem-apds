package org.apds.projects.api.config.filter;

import lombok.NonNull;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class CorsFilter implements WebFilter {

    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    public static final String AUTHORIZATION_DNT = "Authorization,DNT,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range";

    @Override
    public Mono<Void> filter(final ServerWebExchange serverWebExchange, @NonNull final WebFilterChain webFilterChain) {
        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Authorization,DNT,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
        if (serverWebExchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
            serverWebExchange.getResponse().getHeaders().add("Access-Control-Max-Age", "1728000");
            serverWebExchange.getResponse().setStatusCode(HttpStatus.NO_CONTENT);
            return Mono.empty();
        } else {
            serverWebExchange.getResponse().getHeaders().add(ACCESS_CONTROL_EXPOSE_HEADERS, AUTHORIZATION_DNT);
            return webFilterChain.filter(serverWebExchange);
        }
    }
}
