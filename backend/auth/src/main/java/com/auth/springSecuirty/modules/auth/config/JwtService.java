package com.auth.springSecuirty.modules.auth.config;

import io.jsonwebtoken.Claims;

import java.util.function.Function;

public class JwtService { //this class will provide all the jwt service that wil be needed

    private final String SECRET_KEY = "978b4bd050e85b6d227918c9f2f5e9641f3e837377625b15b0c9e39bcfed27fa"; //change in prod to be outside

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){ //this will Extract All Claims Within JWT Token
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    

}
