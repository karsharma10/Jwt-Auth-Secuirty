package com.auth.springSecuirty.utils;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class GetCookie {

    public String extractJwtCookie(HttpServletRequest request){
        String token = null;
        for(Cookie cookie: request.getCookies()){
            if(cookie.getName().equals("accessToken")){
                token = cookie.getValue();
            }
        }
        return token;
    }
}
