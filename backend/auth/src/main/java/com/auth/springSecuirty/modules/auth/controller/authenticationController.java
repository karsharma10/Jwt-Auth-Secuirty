package com.auth.springSecuirty.modules.auth.controller;

import com.auth.springSecuirty.modules.auth.service.AuthenticationService;
import com.auth.springSecuirty.modules.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            return new ResponseEntity<>("User Already Exists In The Database",HttpStatus.NO_CONTENT);
        }
        if(authenticationService.doesUserExist(userDto)){ //if the user exists in the database, then don't register them
            System.out.println("h");
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        else{
            String registerToken = authenticationService.register(userDto);
            return new ResponseEntity<>(registerToken, HttpStatus.OK);
        }
    }
}
