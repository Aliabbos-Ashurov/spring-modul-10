package com.pdp.springm10;

import com.pdp.springm10.entity.AuthUser;
import com.pdp.springm10.repository.AuthUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AuthUserRepositoryTest {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Test
    public void testSaveAndFindUser() {
        AuthUser user = new AuthUser();
        user.setName("John");
        user.setEmail("john@example.com");
        authUserRepository.save(user);

        Optional<AuthUser> foundUser = authUserRepository.findById(user.getId());
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("John");
    }

    @Test
    public void testDeleteUser() {
        AuthUser user = new AuthUser();
        user.setName("Jane");
        user.setEmail("jane@example.com");
        authUserRepository.save(user);

        authUserRepository.deleteById(user.getId());
        Optional<AuthUser> deletedUser = authUserRepository.findById(user.getId());
        assertThat(deletedUser).isNotPresent();
    }
}

