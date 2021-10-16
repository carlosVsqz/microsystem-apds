package org.apds.projects.api.application.auth.service.support;

import org.apds.projects.api.application.auth.entity.User;
import org.apds.projects.api.application.auth.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apds.projects.api.application.auth.repository.ScopeRepository;
import org.apds.projects.api.application.auth.repository.UserRepository;
import org.apds.projects.api.utils.reactive.RxUtil;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserSimpleService implements UserService {

    private final UserRepository userRepository;

    private final ScopeRepository scopeRepository;

    private final CacheManager cacheManager;

    @Qualifier("jdbcScheduler")
    private final Scheduler jdbcScheduler;

    private final TransactionTemplate transactionTemplate;

    @Transactional(readOnly = true)
    public Mono<User> findById(@NonNull String username) {

        return
            RxUtil.cacheMono(
                cacheManager
                , "users"
                , username
                , Mono.defer(() -> transactionTemplate.execute(status ->
                        userRepository.findById(username)
                                      .map(user -> {
                                          Hibernate.initialize(user.getAuthorities());
                                          return user;
                                      })
                                      .map(Mono::just)
                                      .orElseGet(Mono::empty)
                      ))
                      .subscribeOn(jdbcScheduler)
                , User.class
            );
    }

    @Transactional
    public Mono<User> addUser(@NonNull String userId, @NonNull String password) {

        return
            Mono.defer(() -> transactionTemplate.execute(status ->
                {
                    User user = userRepository.findById(userId)
                                              .orElseGet(() -> {
                                                  User newbie = new User();
                                                  newbie.setUsername(userId);
                                                  return newbie;
                                              });
                    user.setPassword(password);
                    user.getAuthorities().clear();
                    user.getAuthorities().addAll(scopeRepository.findAll()); //Add Full Scopes
                    return Mono.just(userRepository.save(user));
                }
            )).subscribeOn(jdbcScheduler)
            ;

    }

}
