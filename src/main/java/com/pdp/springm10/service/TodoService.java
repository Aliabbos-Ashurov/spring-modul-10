package com.pdp.springm10.service;

import com.pdp.springm10.entity.Todo;
import com.pdp.springm10.entity.User;
import com.pdp.springm10.enums.Category;
import com.pdp.springm10.enums.Level;
import com.pdp.springm10.repository.TodoRepository;
import com.pdp.springm10.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 17/September/2024  14:34
 **/
@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    public Todo createTodo(Integer userId, Todo todo) {
        User user = userRepository.findById(userId).orElseThrow();
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Integer id, Todo todo) {
        todo.setId(id);
        return todoRepository.save(todo);
    }

    public void deleteTodo(Integer id) {
        todoRepository.deleteById(id);
    }

    public Todo completeTodo(Integer id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setCompleted(true);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodosByLevel(Level level) {
        return todoRepository.findByLevel(level);
    }

    public List<Todo> getTodosByCategory(Category category) {
        return todoRepository.findByCategory(category);
    }

    public List<Todo> getTodosByDeadLine(LocalDate deadLine) {
        return todoRepository.findByDeadLine(deadLine);
    }

    public Todo getTodoById(Integer id) {
        return todoRepository.findById(id).orElseThrow();
    }
}
