package org.apds.projects.api.config.support;

import lombok.NonNull;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(@NonNull Throwable ex, Method method, @Nullable Object... params) {

        log.error("Async Exception occurred at {} ", method.getName());
        log.error(ex.getLocalizedMessage(), ex);
    }

}
