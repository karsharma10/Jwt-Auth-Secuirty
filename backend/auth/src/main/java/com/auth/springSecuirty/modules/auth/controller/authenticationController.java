package com.auth.springSecuirty.modules.auth.controller;

import com.auth.springSecuirty.modules.auth.service.AuthenticationService;
import com.auth.springSecuirty.modules.user.dto.UserDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class authenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        if(userDto == null){
            return new ResponseEntity<>("Please Give User Credentials",HttpStatus.NO_CONTENT);
        }
        if(authenticationService.doesUserExist(userDto)){ //if the user exists in the database, then don't register them
            return new ResponseEntity<>("User Already Exists In The Database",HttpStatus.METHOD_NOT_ALLOWED);
        }
        else{
            authenticationService.register(userDto);
            return new ResponseEntity<>("User Registered", HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto, HttpServletResponse response){
        if(userDto == null){
            return new ResponseEntity<>("User Already Exists In The Database",HttpStatus.NO_CONTENT);
        }
        String token = authenticationService.authentication(userDto);

        ResponseCookie cookie = ResponseCookie.from("accessToken", token)
                                .httpOnly(true)
                                .secure(false)
                                .path("/")
                                .maxAge(1800)
                                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
