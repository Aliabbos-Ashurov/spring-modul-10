package com.pdp.springm10.entity;

import com.pdp.springm10.enums.Category;
import com.pdp.springm10.enums.Level;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * @author Aliabbos Ashurov
 * @since 17/September/2024  14:25
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Level level;
    private LocalDate deadLine;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
