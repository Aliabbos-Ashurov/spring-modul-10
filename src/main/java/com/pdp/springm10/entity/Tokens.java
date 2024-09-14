package com.pdp.springm10.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  16:18
 **/
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tokens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accessToken;

    @Column(nullable = false, unique = true)
    private String refreshToken;

    @Column(nullable = false, unique = true)
    private String username;
}
