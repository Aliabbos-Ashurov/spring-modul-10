package com.pdp.springm10.listener;

import com.pdp.springm10.entity.User;
import com.pdp.springm10.event.UserCreatedEvent;
import com.pdp.springm10.repository.UserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author Aliabbos Ashurov
 * @since 12/September/2024  14:27
 **/
@Component
public class UserCreatedEventHandler {
    private final UserRepository userRepository;

    public UserCreatedEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        User user = event.getUser();
        user.setCreatedAt(Instant.now());
        userRepository.save(user);
    }
}
