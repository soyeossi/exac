package com.shakiroye.exac.controller;

import com.shakiroye.exac.dto.CreateInvoiceDTO;
import com.shakiroye.exac.dto.InvoiceDTO;
import com.shakiroye.exac.dto.InvoiceTypeDTO;
import com.shakiroye.exac.model.Invoice;
import com.shakiroye.exac.model.InvoiceType;
import com.shakiroye.exac.service.InvoiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice-type")
@RequiredArgsConstructor
public class InvoiceTypeController {

    @Autowired
    private final InvoiceTypeService invoiceTypeService;

    @PostMapping
    public ResponseEntity<InvoiceType> saveInvoiceType(@RequestBody InvoiceTypeDTO invoiceTypeDTO){
        return new ResponseEntity(invoiceTypeService.saveInvoiceType(invoiceTypeDTO) ,
                HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<InvoiceTypeDTO>> getInvoiceTypes(){
        return new ResponseEntity(invoiceTypeService.getInvoiceTypes() ,
                HttpStatus.OK);
    }

}
