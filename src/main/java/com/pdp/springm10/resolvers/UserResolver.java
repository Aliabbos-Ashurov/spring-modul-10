package com.pdp.springm10.resolvers;

import com.pdp.springm10.entity.User;
import com.pdp.springm10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
@Controller
public class UserResolver {

    @Autowired
    private UserService userService;

    public UserResolver(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public ResponseEntity<User> getUserById(@Argument Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @QueryMapping
    public List<User> allUsers() {
        return userService.getAllUsers();
    }
}
