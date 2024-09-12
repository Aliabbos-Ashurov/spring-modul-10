package com.pdp.springm10.event;

import com.pdp.springm10.entity.User;
import lombok.Getter;

/**
 * @author Aliabbos Ashurov
 * @since 12/September/2024  14:19
 **/
@Getter
public class UserCreatedEvent {
    private final User user;

    public UserCreatedEvent(User user) {
        this.user = user;
    }
}
