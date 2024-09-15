package com.pdp.springm10.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Aliabbos Ashurov
 * @since 15/September/2024  14:37
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auth_users")
@Entity
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
}
