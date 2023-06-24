package com.shakiroye.exac.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private String name;
    private String acronym;
    private Long nif;
    private String address;
    private String rccm;
    private String phoneNumber;
    private String email;
}
