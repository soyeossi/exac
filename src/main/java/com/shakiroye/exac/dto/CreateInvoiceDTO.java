package com.shakiroye.exac.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceDTO {
    private String description;
    private String normalYear;
    private List<HonoraireDTO> honoraires;
    private List<DebourDTO> debours;

    private Long idClient;
    private Long idInvoiceType;

}
