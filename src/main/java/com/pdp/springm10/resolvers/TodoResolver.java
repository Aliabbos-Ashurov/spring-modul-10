package com.pdp.springm10.resolvers;

import com.pdp.springm10.dto.TodoCreateDto;
import com.pdp.springm10.dto.TodoUpdateDto;
import com.pdp.springm10.entity.Todo;
import com.pdp.springm10.entity.User;
import com.pdp.springm10.enums.Category;
import com.pdp.springm10.enums.Level;
import com.pdp.springm10.service.TodoService;
import com.pdp.springm10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 17/September/2024  14:48
 **/
@Component
@Controller
public class TodoResolver {

    @Autowired
    private final TodoService todoService;

    @Autowired
    private final UserService userService;

    public TodoResolver(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @QueryMapping
    public List<Todo> todosByLevel(@Argument(name = "level") Level level) {
        return todoService.getTodosByLevel(level);
    }

    @QueryMapping
    public List<Todo> todosByCategory(@Argument(name = "category") Category category) {
        return todoService.getTodosByCategory(category);
    }

    @QueryMapping
    public List<Todo> todosByDeadLine(@Argument(name = "deadLine") String deadLine) {
        return todoService.getTodosByDeadLine(LocalDate.parse(deadLine));
    }

    @QueryMapping
    public List<Todo> todosByUser(@Argument(name = "userId") Integer userId) {
        User user = userService.getUserById(userId);
        return user.getTodos();
    }


    @MutationMapping
    public Todo createTodo(@Argument(name = "dto") TodoCreateDto dto) {
        Todo todo = new Todo();
        todo.setTitle(dto.title());
        todo.setDescription(dto.description());
        todo.setCategory(dto.category());
        todo.setLevel(dto.level());
        todo.setDeadLine(LocalDate.parse(dto.deadLine()));
        todo.setCompleted(false);
        return todoService.createTodo(dto.userId(), todo);
    }

    @MutationMapping
    public Todo updateTodo(@Argument(name = "dto") TodoUpdateDto dto) {
        Todo todo = todoService.getTodoById(dto.id());
        if (todo != null) {
            todo.setTitle(dto.title());
            todo.setDescription(dto.description());
            todo.setCategory(dto.category());
            todo.setLevel(dto.level());
            todo.setDeadLine(LocalDate.parse(dto.deadLine()));
            todo.setCompleted(dto.completed());
        }
        assert todo != null;
        return todoService.updateTodo(dto.id(), todo);
    }

    @MutationMapping
    public Boolean deleteTodo(@Argument(name = "id") Integer id) {
        try {
            todoService.deleteTodo(id);
            return true;
        } catch (Exception e) {
            // Log the exception for debugging purposes
            // logger.error("Error deleting todo with id " + id, e);
            return false;
        }
    }

    @MutationMapping
    public Todo completeTodo(@Argument(name = "id") Integer id) {
        return todoService.completeTodo(id);
    }

}
