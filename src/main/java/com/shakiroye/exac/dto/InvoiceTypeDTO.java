package com.shakiroye.exac.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceTypeDTO {

    private Long idInvoiceType;
    private String label;

}
