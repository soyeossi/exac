package com.shakiroye.exac.service;

import com.shakiroye.exac.dto.LoginRequest;
import com.shakiroye.exac.dto.LoginResponse;
import com.shakiroye.exac.dto.RegisterRequest;
import com.shakiroye.exac.dto.RegisterResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
    RegisterResponse update(RegisterRequest registerRequest);
}
