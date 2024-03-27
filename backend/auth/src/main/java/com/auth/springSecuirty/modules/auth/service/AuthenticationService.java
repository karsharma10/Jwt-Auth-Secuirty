package com.auth.springSecuirty.modules.auth.service;

import com.auth.springSecuirty.modules.user.dto.UserDto;
import com.auth.springSecuirty.modules.user.entity.User;
import com.auth.springSecuirty.modules.user.mapper.UserMapper;
import com.auth.springSecuirty.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserMapper userMapper;

    public String register(UserDto userDto){
        User userMapFromDto = userMapper.userMapDtoToEntity(userDto);

        userRepository.save(userMapFromDto);

        var jwtToken = jwtService.generateToken(userMapFromDto);

        return jwtToken;
    }

    

}
