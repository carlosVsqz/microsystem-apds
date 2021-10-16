package org.apds.projects.api.application.auth.service;

import org.apds.projects.api.application.auth.entity.User;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> findById(String userId);

    Mono<User> addUser(String userId, String password);

}
