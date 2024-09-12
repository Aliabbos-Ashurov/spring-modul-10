package com.pdp.springm10.event;

import com.pdp.springm10.entity.User;
import lombok.Getter;

/**
 * @author Aliabbos Ashurov
 * @since 12/September/2024  14:23
 **/
@Getter
public class UserUpdatedEvent {
    private final User user;

    public UserUpdatedEvent(User user) {
        this.user = user;
    }
}
