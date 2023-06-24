package com.shakiroye.exac.service;

import com.shakiroye.exac.dto.InvoiceTypeDTO;
import com.shakiroye.exac.model.InvoiceType;

import java.util.List;

public interface InvoiceTypeService {

    InvoiceTypeDTO saveInvoiceType(InvoiceTypeDTO invoiceTypeDTO);
    InvoiceTypeDTO getInvoiceType(Long idInvoiceType);
    List<InvoiceTypeDTO> getInvoiceTypes();

}
