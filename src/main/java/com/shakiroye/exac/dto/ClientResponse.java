package com.shakiroye.exac.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private Map client;
    private String message;

}
