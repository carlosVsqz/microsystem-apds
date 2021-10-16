package org.apds.projects.api.config;

import org.apds.projects.api.config.support.LocaleResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.server.i18n.LocaleContextResolver;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

@Configuration
public class LocaleConfig extends DelegatingWebFluxConfiguration {

    @Override
    protected LocaleContextResolver createLocaleContextResolver() {
        return new LocaleResolver();
    }

}
