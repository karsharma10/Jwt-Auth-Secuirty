package com.auth.springSecuirty.utils;

import com.auth.springSecuirty.modules.enums.UserRoles;
import com.auth.springSecuirty.modules.user.entity.User;

public class TestData {

    public static User createTestUserA(){
        return User.builder()
                .id(1)
                .firstname("TestA")
                .lastname("TestA")
                .email("testa@gmail.com")
                .password("123")
                .role(UserRoles.USER)
                .build();
    }
}
