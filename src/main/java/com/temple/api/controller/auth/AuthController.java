package com.temple.api.controller.auth;


import com.temple.api.payload.auth.JWTAuthResponse;
import com.temple.api.payload.auth.LoginDto;
import com.temple.api.payload.auth.RegisterDto;
import com.temple.api.service.auth.AuthService;
import com.temple.api.utils.ContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    private ContextHolder contextHolder;

    public AuthController(AuthService authService,
                          ContextHolder contextHolder
    ) {
        this.authService = authService;
        this.contextHolder = contextHolder;
    }


    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(
            HttpServletRequest request,
            @RequestParam String finYear,
            @RequestBody LoginDto loginDto
    ) {
        HttpSession session = request.getSession();
        session.setAttribute("finYear", finYear);

        String token = authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }


    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}