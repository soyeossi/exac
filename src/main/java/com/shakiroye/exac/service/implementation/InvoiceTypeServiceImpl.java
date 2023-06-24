package com.shakiroye.exac.service.implementation;

import com.shakiroye.exac.dto.InvoiceTypeDTO;
import com.shakiroye.exac.model.Invoice;
import com.shakiroye.exac.model.InvoiceType;
import com.shakiroye.exac.repository.InvoiceTypeRepo;
import com.shakiroye.exac.service.InvoiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceTypeServiceImpl implements InvoiceTypeService {

    private final InvoiceTypeRepo invoiceTypeRepo;

    @Override
    public InvoiceTypeDTO saveInvoiceType(InvoiceTypeDTO invoiceTypeDTO) {
        var invoiceType = InvoiceType.builder()
                .label(invoiceTypeDTO.getLabel())
                .build();
        return invoiceTypeRepo.save(invoiceType).toDTO();
    }

    @Override
    public InvoiceTypeDTO getInvoiceType(Long idInvoiceType) {
        return invoiceTypeRepo.findById(idInvoiceType).orElseThrow(() -> new IllegalArgumentException("Invoice type not found")).toDTO();
    }

    @Override
    public List<InvoiceTypeDTO> getInvoiceTypes() {
        List<InvoiceTypeDTO> invoiceTypes = new ArrayList<>();
        for (InvoiceType invoiceType :
                invoiceTypeRepo.findAll()) {
            invoiceTypes.add(invoiceType.toDTO());
        }
        return invoiceTypes;
    }
}
