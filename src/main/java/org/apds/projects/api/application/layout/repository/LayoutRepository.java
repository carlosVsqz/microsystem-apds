package org.apds.projects.api.application.layout.repository;

import org.apds.projects.api.application.layout.entity.LayoutPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Repository;

@Repository
public interface LayoutRepository extends JpaRepository<LayoutPath, Long> {
}
