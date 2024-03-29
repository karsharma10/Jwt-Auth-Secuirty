package com.auth.springSecuirty.UserRepositoryTests;

import com.auth.springSecuirty.modules.enums.UserRoles;
import com.auth.springSecuirty.modules.user.entity.User;
import com.auth.springSecuirty.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryIntegrationTests {

    private final UserRepository userRepository;


    @Autowired
    public UserRepositoryIntegrationTests(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void TestThatUserRepositoryCorrectlyCreatesAndFetchesUser(){
        User testUser = User.builder()
                .id(1)
                .firstname("test1")
                .lastname("test1")
                .email("test@gmail.com")
                .password("123")
                .role(UserRoles.USER)
                .build();

        userRepository.save(testUser);

        Optional<User> findTestUserById = userRepository.findById(1);

        assertThat(findTestUserById).isPresent();


    }
}
