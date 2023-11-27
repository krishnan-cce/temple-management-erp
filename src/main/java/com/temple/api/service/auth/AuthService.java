package com.temple.api.service.auth;

import com.temple.api.payload.auth.LoginDto;
import com.temple.api.payload.auth.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
