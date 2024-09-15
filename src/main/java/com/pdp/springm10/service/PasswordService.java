package com.pdp.springm10.service;

import com.pdp.springm10.entity.Password;
import com.pdp.springm10.repository.PasswordRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final PasswordRepository repository;

    public void addPassword(String service, String password) {
        Password pw = new Password();
        pw.setService(service);
        pw.setPassword(password);
        repository.save(pw);
    }

    public String getPassword(String service) {
        Password pw = repository.findByService(service);
        return pw != null ? pw.getPassword() : "Password not found";
    }

    public String createPasswordWithLength(int length) {
        if (length > 32) {
            throw new IllegalArgumentException("Length must be 32 or less");
        }
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, length);
    }

    public String createStrongPassword() {
        return UUID.randomUUID().toString().replaceAll("-", "") +
                UUID.randomUUID().toString().replaceAll("-", "");
    }
}
