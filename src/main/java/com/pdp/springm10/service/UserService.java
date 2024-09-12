package com.pdp.springm10.service;

import com.pdp.springm10.entity.User;
import com.pdp.springm10.event.UserCreatedEvent;
import com.pdp.springm10.event.UserUpdatedEvent;
import com.pdp.springm10.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Aliabbos Ashurov
 * @since 12/September/2024  14:24
 **/
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    public UserService(UserRepository userRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        eventPublisher.publishEvent(new UserCreatedEvent(savedUser));
        return savedUser;
    }

    @Transactional
    public User updateUser(User user) {
        User updatedUser = userRepository.save(user);
        eventPublisher.publishEvent(new UserUpdatedEvent(updatedUser));
        return updatedUser;
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
