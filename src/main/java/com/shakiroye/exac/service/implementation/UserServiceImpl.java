package com.shakiroye.exac.service.implementation;

import com.shakiroye.exac.dto.LoginRequest;
import com.shakiroye.exac.dto.LoginResponse;
import com.shakiroye.exac.dto.RegisterRequest;
import com.shakiroye.exac.dto.RegisterResponse;
import com.shakiroye.exac.model.User;
import com.shakiroye.exac.repository.UserRepo;
import com.shakiroye.exac.security.JwtService;
import com.shakiroye.exac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        userRepo.save(user);

        Map userInfo = new HashMap<>();
        userInfo.put("idUser" , user.getIdUser());
        userInfo.put("firstName" , user.getFirstName());
        userInfo.put("lastName" , user.getLastName());
        userInfo.put("email" , user.getEmail());

        return RegisterResponse.builder()
                .user(userInfo)
                .message("Register successfully")
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        var user = userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        Map userInfo = new HashMap<>();
        userInfo.put("idUser" , user.getIdUser());
        userInfo.put("firstName" , user.getFirstName());
        userInfo.put("lastName" , user.getLastName());
        userInfo.put("email" , user.getEmail());

        return LoginResponse.builder()
                .token(jwtToken)
                .user(userInfo)
                .message("logged successfully")
                .build();
    }

    @Override
    public RegisterResponse update(RegisterRequest registerRequest) {
        return null;
    }
}
