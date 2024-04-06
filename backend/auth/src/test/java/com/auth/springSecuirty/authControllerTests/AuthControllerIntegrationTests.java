package com.auth.springSecuirty.authControllerTests;


import com.auth.springSecuirty.modules.auth.controller.AuthenticationController;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class AuthControllerIntegrationTests {

    private AuthenticationController authenticationController;

    @Autowired
    public AuthControllerIntegrationTests(AuthenticationController authenticationController){
        this.authenticationController = authenticationController;
    }

}
