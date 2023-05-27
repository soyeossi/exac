package com.shakiroye.exac.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

    private String name;
    private Long nif;
    private String address;
    private String rccm;
    private String phoneNumber;
    private String email;
}
