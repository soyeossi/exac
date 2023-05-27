package com.shakiroye.exac.controller;

import com.shakiroye.exac.dto.CreateInvoiceDTO;
import com.shakiroye.exac.dto.InvoiceDTO;
import com.shakiroye.exac.dto.RegisterResponse;
import com.shakiroye.exac.model.Invoice;
import com.shakiroye.exac.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    @Autowired
    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Invoice> saveInvoice(@RequestBody CreateInvoiceDTO createInvoiceDTO){
        return new ResponseEntity(invoiceService.saveInvoice(createInvoiceDTO) ,
                HttpStatus.CREATED);
    }


    @GetMapping("all")
    public ResponseEntity<List<InvoiceDTO>> getInvoices(){
        return new ResponseEntity(invoiceService.getInvoices() ,
                HttpStatus.OK);
    }

    @GetMapping("all/by-client/{clientId}")
    public ResponseEntity<List<InvoiceDTO>> getClientInvoices(@PathVariable("clientId") Long clientId){
        return ResponseEntity.ok().body(invoiceService.getInvoicesByClient(clientId));
    }

    @GetMapping("{invoiceId}")
    public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable("invoiceId") Long clientId){
        return ResponseEntity.ok().body(invoiceService.getInvoice(clientId));
    }
}
