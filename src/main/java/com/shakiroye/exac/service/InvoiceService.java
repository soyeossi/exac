package com.shakiroye.exac.service;

import com.shakiroye.exac.dto.CreateInvoiceDTO;
import com.shakiroye.exac.dto.DebourDTO;
import com.shakiroye.exac.dto.HonoraireDTO;
import com.shakiroye.exac.dto.InvoiceDTO;
import com.shakiroye.exac.model.Client;
import com.shakiroye.exac.model.Debour;
import com.shakiroye.exac.model.Honoraire;
import com.shakiroye.exac.model.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice saveInvoice(CreateInvoiceDTO createInvoiceDTO);
    InvoiceDTO getInvoice(Long idInvoice);
    List<InvoiceDTO> getInvoices();
    List<InvoiceDTO> getInvoicesByClient(Long clientId);

    List<Debour> saveDebours(List<DebourDTO> debourDTOList, Invoice invoice);
    List<Honoraire> saveHonoraires(List<HonoraireDTO> honoraireDTOList, Invoice invoice);
}
