package com.shakiroye.exac.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HonoraireDTO {

    private Long idHonoraireDTO;
    private String libelle;
    private Double montant;

}
