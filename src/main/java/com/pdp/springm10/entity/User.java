package com.pdp.springm10.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 17/September/2024  14:26
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Todo> todos;
}