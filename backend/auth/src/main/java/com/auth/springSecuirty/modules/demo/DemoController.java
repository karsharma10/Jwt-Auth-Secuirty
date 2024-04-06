package com.auth.springSecuirty.modules.demo;


import com.auth.springSecuirty.modules.auth.service.JwtService;
import com.auth.springSecuirty.utils.GetCookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    private final GetCookie getCookie;

    private final JwtService jwtService;

    public DemoController(GetCookie getCookie, JwtService jwtService) {
        this.getCookie = getCookie;
        this.jwtService = jwtService;
    }

    @GetMapping("/hello-secured")
    public ResponseEntity<String> secureEndpointHello(HttpServletRequest request){
        String token = getCookie.extractJwtCookie(request);

        String username = jwtService.extractUsername(token);

        return new ResponseEntity<>("hello "+username, HttpStatus.OK);
    }
}
