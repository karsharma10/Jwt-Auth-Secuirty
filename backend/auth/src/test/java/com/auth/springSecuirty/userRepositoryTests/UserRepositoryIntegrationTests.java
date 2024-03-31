package com.auth.springSecuirty.userRepositoryTests;

import com.auth.springSecuirty.modules.enums.UserRoles;
import com.auth.springSecuirty.modules.user.entity.User;
import com.auth.springSecuirty.modules.user.repository.UserRepository;
import com.auth.springSecuirty.utils.TestData;
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
@Transactional
public class UserRepositoryIntegrationTests {

    private final UserRepository userRepository;


    @Autowired
    public UserRepositoryIntegrationTests(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void TestThatUserRepositoryCorrectlyCreatesAndFetchesUser(){
        User testUser = TestData.createTestUserA();

        userRepository.save(testUser);

        Optional<User> findTestUserById = userRepository.findById(1);

        assertThat(findTestUserById).isPresent(); //assert that it is present
        assertThat(findTestUserById.get().getId()).isEqualTo(1);
        assertThat(findTestUserById.get().getFirstname()).isEqualTo("TestA");
        assertThat(findTestUserById.get().getLastname()).isEqualTo("TestA");
        assertThat(findTestUserById.get().getEmail()).isEqualTo("testa@gmail.com");
        assertThat(findTestUserById.get().getPassword()).isEqualTo("123");
        assertThat(findTestUserById.get().getRole()).isEqualTo(UserRoles.USER);

    }
}
