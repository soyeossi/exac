package com.shakiroye.exac.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DebourDTO {

    private Long idDebourDTO;
    private String libelle;
    private Double montant;

}
