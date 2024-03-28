package org.ekl.backend.ws.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(schema = "EKL", name="GROUP")
public class Group {

    /* for JPA version 2.3 or lower
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Type(type = "uuid-char")
     */
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column
    private String name;
}
