package org.apds.projects.api.application.layout.service.support;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apds.projects.api.application.auth.service.UserService;
import org.apds.projects.api.application.layout.entity.LayoutPath;
import org.apds.projects.api.application.layout.repository.LayoutRepository;
import org.apds.projects.api.application.layout.service.LayoutService;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimpleLayoutService implements LayoutService {

    private final LayoutRepository layoutRepository;

    private final UserService userService;

    private final CacheManager cacheManager;

    private final TransactionTemplate transactionTemplate;

    @Override
    @Transactional
    public List<LayoutPath> getItemsPathParamToUser(String username) {
        return null;
    }

    @Override
    public Flux<LayoutPath> findItemsPath() {
        return Flux.;
    }
}
