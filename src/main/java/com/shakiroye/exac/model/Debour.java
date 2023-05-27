package com.shakiroye.exac.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Debour{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDebour;
    private String libelle;
    private Double montant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idInvoice", nullable = false)
    private Invoice invoice;

}