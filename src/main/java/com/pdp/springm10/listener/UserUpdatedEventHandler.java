package com.pdp.springm10.listener;

import com.pdp.springm10.entity.User;
import com.pdp.springm10.event.UserUpdatedEvent;
import com.pdp.springm10.repository.UserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
/**
 * @author Aliabbos Ashurov
 * @since 12/September/2024  14:28
 **/
@Component
public class UserUpdatedEventHandler {
    private final UserRepository userRepository;

    public UserUpdatedEventHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener
    public void handleUserUpdatedEvent(UserUpdatedEvent event) {
        User user = event.getUser();
        User existingUser = userRepository.findById(user.getId()).orElseThrow();
        existingUser.setUpdatedAt(Instant.now());
        userRepository.save(existingUser);
    }
}
