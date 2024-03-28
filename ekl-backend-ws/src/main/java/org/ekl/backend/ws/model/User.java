package org.ekl.backend.ws.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Validated
@Data
@NoArgsConstructor
@Entity
@Table(name = "USER", schema = "EKL")
public class  User{

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private UUID id;

    @Email
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "FAMILYNAME")
    private String familyname;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @Transient
    private String token;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "id")
    private List<Role> roles;
}
