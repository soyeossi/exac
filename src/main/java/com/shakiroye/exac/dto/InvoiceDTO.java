package com.shakiroye.exac.dto;

import com.shakiroye.exac.model.Invoice;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private Long idInvoice;
    private String invoiceNumber;
    private String description;
    private Double tva;
    private Double totalTTC;
    private Double rsps;
    private Double net;
    private List<HonoraireDTO> honoraires;
    private List<DebourDTO> debours;
    private LocalDate creationDate;

    private ClientDTO client;
    private InvoiceTypeDTO invoiceType;

}

