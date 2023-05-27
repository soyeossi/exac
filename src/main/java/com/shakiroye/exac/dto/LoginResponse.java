package com.shakiroye.exac.dto;

import com.shakiroye.exac.model.User;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String message;
    private Map user;
}
