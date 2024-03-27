package com.auth.springSecuirty.modules.auth.config;

import com.auth.springSecuirty.modules.auth.service.JwtService;
import com.auth.springSecuirty.utils.GetCookie;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService; //inject JwtService

    private final UserDetailsService userDetailsService;

    private final GetCookie getCookie;

    @Override
    protected void doFilterInternal(@NonNull  HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization"); //this will get the authorization from header

        String jwt = null; //set jwt token to null for now
        String userEmail = null; //set username to null

        if (authHeader != null && authHeader.startsWith("Bearer ")){ //check if the token is being passed in the auth header
            jwt = authHeader.substring(7);
        }
        else if(request.getCookies() != null){
            jwt = getCookie.extractJwtCookie(request); //call get cookie method if the jwt token is in the cookie path.
        }

        //if jwt is equal to null then do filter:
        if(jwt == null){
            filterChain.doFilter(request, response);
        }

        userEmail = jwtService.extractUsername(jwt); //grab the useremail from the jwt token

        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){ //if the useremail is not null and the authentication is not yet set then:
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if(jwtService.isTokenValid(jwt,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);

    }
}
