package org.apds.projects.api.application.auth.repository;

import org.apds.projects.api.application.auth.entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScopeRepository extends JpaRepository<Scope, String> {
}
