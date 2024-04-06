package com.auth.springSecuirty.authControllerTests;


import com.auth.springSecuirty.modules.auth.controller.AuthenticationController;
import com.auth.springSecuirty.modules.demo.DemoController;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class AuthControllerIntegrationTests {

    private final AuthenticationController authenticationController;

    private final DemoController demoController;

    private final MockMvc mockMvc;


    @Autowired
    public AuthControllerIntegrationTests(AuthenticationController authenticationController, DemoController demoController, MockMvc mockMvc){
        this.authenticationController = authenticationController;
        this.demoController = demoController;
        this.mockMvc = mockMvc;
    }

    @Test
    public void testThatUnAuthorizedUserShouldNotAccessAuthorizedAPI() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/demo-controller/hello-secured")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)

        )
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}
