package com.pdp.springm10.handler.exception;

import java.text.MessageFormat;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  12:33
 **/
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}
