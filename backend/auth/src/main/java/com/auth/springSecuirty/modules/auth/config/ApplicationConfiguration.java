package com.auth.springSecuirty.modules.auth.config;

import com.auth.springSecuirty.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){ //find user in the database by using a lambda function:
        return username -> userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found!"));
    }

}
