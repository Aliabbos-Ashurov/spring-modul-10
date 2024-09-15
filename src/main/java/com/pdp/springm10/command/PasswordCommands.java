package com.pdp.springm10.command;

import com.pdp.springm10.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * @author Aliabbos Ashurov
 * @since 15/September/2024  15:49
 **/
@ShellComponent
@RequiredArgsConstructor
public class PasswordCommands {

    @Autowired
    private PasswordService passwordService;

    @ShellMethod(value = "Add a password for a service", key = "add")
    public void addPassword(@ShellOption(help = "service for") String service,
                            @ShellOption(help = "your real password for service") String password) {
        passwordService.addPassword(service, password);
    }

    @ShellMethod(value = "Get a password for a service", key = "get")
    public String getPassword(String service) {
        return passwordService.getPassword(service);
    }

    @ShellMethod(value = "Create a password with specified length", key = "generate")
    public String createPasswordWithLength(@ShellOption(defaultValue = "16") int length) {
        return passwordService.createPasswordWithLength(length);
    }

    @ShellMethod(value = "Create a strong password", key = "generate-strong")
    public String createStrongPassword() {
        return passwordService.createStrongPassword();
    }
}
