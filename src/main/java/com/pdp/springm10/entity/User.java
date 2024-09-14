package com.pdp.springm10.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 13/September/2024  13:01
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends Auditable {

    private String fullname;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String username;


    @Column(nullable = false, length = 100)
    private String password;
}

