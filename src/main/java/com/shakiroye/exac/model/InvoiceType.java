package com.shakiroye.exac.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "invoiceType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices;

    public InvoiceTypeDTO toDTO(){
        InvoiceTypeDTO invoiceTypeDTO = new InvoiceTypeDTO();
        invoiceTypeDTO.setIdInvoiceType(this.idInvoiceType);
        invoiceTypeDTO.setLabel(this.label);
        return invoiceTypeDTO;
    }

}
