package com.shakiroye.exac.model;

import com.shakiroye.exac.dto.InvoiceTypeDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvoiceType;
    private String label;

    public InvoiceTypeDTO toDTO(){
        InvoiceTypeDTO invoiceTypeDTO = new InvoiceTypeDTO();
        invoiceTypeDTO.setIdInvoiceType(this.idInvoiceType);
        invoiceTypeDTO.setLabel(this.label);
        return invoiceTypeDTO;
    }

}
