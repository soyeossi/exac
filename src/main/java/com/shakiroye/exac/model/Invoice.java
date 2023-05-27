package com.shakiroye.exac.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shakiroye.exac.dto.InvoiceDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvoice;
    private String invoiceNumber;
    private String description;
    private Double tva;
    private Double totalTTC;
    private Double rsps;
    private Double net;
    private LocalDate creationDate;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Honoraire> honoraires;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Debour> debours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient")
    private Client client;

    @PrePersist
    public void prePersist() {
        creationDate = LocalDate.now();
    }

}



