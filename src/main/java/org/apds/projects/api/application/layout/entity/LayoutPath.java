package org.apds.projects.api.application.layout.entity;

import lombok.Data;
//import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table
@Entity
public class LayoutPath implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String label;
    String path;

}
