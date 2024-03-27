package com.auth.springSecuirty.modules.auth.service;

import com.auth.springSecuirty.modules.enums.UserRoles;
import com.auth.springSecuirty.modules.user.dto.UserDto;
import com.auth.springSecuirty.modules.user.entity.User;
import com.auth.springSecuirty.modules.user.mapper.UserMapper;
import com.auth.springSecuirty.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserMapper userMapper;

    public Boolean register(UserDto userDto){
        User userMapFromDto = userMapper.userMapDtoToEntity(userDto);

        userMapFromDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userMapFromDto.setRole(UserRoles.USER);

        User saveUser = userRepository.save(userMapFromDto);


        return true;
    }

    public boolean doesUserExist(UserDto userDto){
        User userMapFromDto = userMapper.userMapDtoToEntity(userDto);
        String userEmail = userMapFromDto.getEmail();

        Optional<User> findUserByEmail = userRepository.findByEmail(userEmail);

        return findUserByEmail.isPresent();
    }

    public String authentication(UserDto userDto){
        User userMapFromDto = userMapper.userMapDtoToEntity(userDto);

        //will check if username and password is correct:
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userMapFromDto.getEmail(), userMapFromDto.getPassword())
        );

        var user = userRepository.findByEmail(userMapFromDto.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return jwtToken;

    }

}
