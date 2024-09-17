package com.pdp.springm10.repository;

import com.pdp.springm10.entity.Todo;
import com.pdp.springm10.enums.Category;
import com.pdp.springm10.enums.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 17/September/2024  14:30
 **/
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByLevel(Level level);

    List<Todo> findByCategory(Category category);

    List<Todo> findByDeadLine(LocalDate deadLine);
}
