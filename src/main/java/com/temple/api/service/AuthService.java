package com.temple.api.service;

import com.temple.api.payload.LoginDto;
import com.temple.api.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
