package com.shakiroye.exac.repository;

import com.shakiroye.exac.model.Client;
import com.shakiroye.exac.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
    List<Invoice> findByClient(Client client);
}
