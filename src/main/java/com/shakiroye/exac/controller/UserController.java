package com.shakiroye.exac.controller;

import com.shakiroye.exac.dto.LoginRequest;
import com.shakiroye.exac.dto.LoginResponse;
import com.shakiroye.exac.dto.RegisterRequest;
import com.shakiroye.exac.dto.RegisterResponse;
import com.shakiroye.exac.model.User;
import com.shakiroye.exac.repository.UserRepo;
import com.shakiroye.exac.security.JwtService;
import com.shakiroye.exac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().
                path("/api/v1/user/register").toUriString());

        Optional<User> userOptional = userRepo.
                findByEmail(registerRequest.getEmail());

        final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$" , Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(registerRequest.getEmail());

        if (!matcher.find()) {
            return new ResponseEntity(RegisterResponse.builder()
                    .message("Invalid Email")
                    .build() , HttpStatus.BAD_REQUEST);
        }

        if (registerRequest.getPassword().length() < 8) {
            return new ResponseEntity(RegisterResponse.builder()
                    .message("Password must exceed 8 character")
                    .build() , HttpStatus.BAD_REQUEST);
        }

        if (userOptional.isPresent()) {
            return new ResponseEntity(RegisterResponse.builder()
                    .message("email '" + registerRequest.getEmail() + "' already taken")
                    .build() ,
                    HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.created(uri).body(userService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody LoginRequest loginRequest
    ) {

            Optional<User> userOptional = userRepo.
                    findByEmail(loginRequest.getEmail());

            if (userOptional.isPresent()) {
                return ResponseEntity.ok(userService.login(loginRequest));
            }
            return new ResponseEntity<LoginResponse>(LoginResponse.builder()
                    .message("User not found")
                    .build() , HttpStatus.BAD_REQUEST);


    }
}
