package com.auth.springSecuirty.modules.auth.config;

import com.auth.springSecuirty.utils.GetCookie;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements OncePerRequestFilter {

    private final JwtService jwtService; //inject JwtService

    private final UserDetailsService userDetailsService;

    private final GetCookie getCookie;

    @Override
    protected void doFilterInternal(@NonNull  HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization"); //this will get the authorization from header

        String jwt = null; //set jwt token to null for now
        String username = null; //set username to null

        if (authHeader != null && authHeader.startsWith("Bearer ")){ //check if the token is being passed in the auth header
            jwt = authHeader.substring(7);
        }
        else if(request.getCookies() != null){
            jwt = getCookie.extractJwtCookie(request); //call get cookie method if the jwt token is in the cookie path.
        }




    }
}
