package org.apds.projects.api.application.layout.service;

import org.apds.projects.api.application.layout.entity.LayoutPath;
import reactor.core.publisher.Flux;

import java.util.List;

public interface LayoutService {
    List<LayoutPath> getItemsPathParamToUser(String username);

    Flux<LayoutPath> findItemsPath();
}
