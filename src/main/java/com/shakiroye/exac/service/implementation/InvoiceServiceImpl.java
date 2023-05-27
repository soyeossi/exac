package com.shakiroye.exac.service.implementation;

import com.shakiroye.exac.dto.*;
import com.shakiroye.exac.model.Client;
import com.shakiroye.exac.model.Debour;
import com.shakiroye.exac.model.Honoraire;
import com.shakiroye.exac.model.Invoice;
import com.shakiroye.exac.repository.ClientRepo;
import com.shakiroye.exac.repository.DebourRepo;
import com.shakiroye.exac.repository.HonoraireRepo;
import com.shakiroye.exac.repository.InvoiceRepo;
import com.shakiroye.exac.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final DebourRepo debourRepo;
    private final HonoraireRepo honoraireRepo;
    private final ClientRepo clientRepo;


    @Override
    public Invoice saveInvoice(CreateInvoiceDTO invoiceDTO) {
        var totalMontantDebour = 0;
        var totalMontantHonoraire = 0;

        for (DebourDTO debourDTO:invoiceDTO.getDebours()) {
            totalMontantDebour += debourDTO.getMontant();
        }

        for (HonoraireDTO honoraireDTO:invoiceDTO.getHonoraires()) {
            totalMontantHonoraire += honoraireDTO.getMontant();
        }

        Double tva = totalMontantHonoraire * 0.18;
        Double totalTTC = tva + totalMontantHonoraire + totalMontantDebour;
        Double rsps = totalMontantHonoraire * 0.15;
        Double net = totalTTC - rsps;

        Client client = clientRepo.findById(invoiceDTO.getIdClient()).orElseThrow(
                (() -> new IllegalStateException(
                        "Client with id " + invoiceDTO.getIdClient() + " does not exist")
                )
        );
        System.out.println("saving");

        var currentYear = LocalDate.now(ZoneId.of("UTC")).getYear();
        var invoiceNumber = invoiceDTO.getInvoiceNumber()+ "UMTS/"+ currentYear +"/"+currentYear+"/ADS";

        var invoice = Invoice.builder()
                .client(client)
                .description(invoiceDTO.getDescription())
                .invoiceNumber(invoiceNumber)
                .tva(tva)
                .totalTTC(totalTTC)
                .rsps(rsps)
                .net(net)
                .build();
        final Invoice savedInvoice = invoiceRepo.save(invoice);
        saveDebours(invoiceDTO.getDebours(), savedInvoice);
        saveHonoraires(invoiceDTO.getHonoraires(), savedInvoice);

        return savedInvoice;

    }

    @Override
    public InvoiceDTO getInvoice(Long idInvoice) {

        final Invoice invoice = invoiceRepo.findById(idInvoice).orElseThrow(() -> new IllegalArgumentException("Invoice not found"));

        List<DebourDTO> debourDTOList = new ArrayList<>();
        List<HonoraireDTO> honoraireDTOList = new ArrayList<>();

        for (Debour debour :
                invoice.getDebours()) {
            DebourDTO debourDTO = DebourDTO.builder()
                    .idDebourDTO(debour.getIdDebour())
                    .libelle(debour.getLibelle())
                    .montant(debour.getMontant())
                    .build();
            debourDTOList.add(debourDTO);
        }

        for (Honoraire honoraire:
                invoice.getHonoraires()) {
            HonoraireDTO honoraireDTO = HonoraireDTO.builder()
                    .idHonoraireDTO(honoraire.getIdHonoraire())
                    .libelle(honoraire.getLibelle())
                    .montant(honoraire.getMontant())
                    .build();
            honoraireDTOList.add(honoraireDTO);
        }

        var clientDTO = ClientDTO.builder()
                .name(invoice.getClient().getName())
                .nif(invoice.getClient().getNif())
                .address(invoice.getClient().getAddress())
                .phoneNumber(invoice.getClient().getPhoneNumber())
                .rccm(invoice.getClient().getRccm())
                .email(invoice.getClient().getEmail())
                .build();

        InvoiceDTO invoiceDTO = InvoiceDTO.builder()
                .idInvoice(invoice.getIdInvoice())
                .invoiceNumber(invoice.getInvoiceNumber())
                .description(invoice.getDescription())
                .tva(invoice.getTva())
                .totalTTC(invoice.getTotalTTC())
                .rsps(invoice.getRsps())
                .net(invoice.getNet())
                .debours(debourDTOList)
                .honoraires(honoraireDTOList)
                .creationDate(invoice.getCreationDate())
                .client(clientDTO)
                .build();
        return invoiceDTO;
    }

    @Override
    public List<InvoiceDTO> getInvoices() {
        List<InvoiceDTO> invoices = new ArrayList<>();

        for (Invoice invoice :
                invoiceRepo.findAll()) {

        List<DebourDTO> debourDTOList = new ArrayList<>();
        List<HonoraireDTO> honoraireDTOList = new ArrayList<>();

            for (Debour debour :
                    invoice.getDebours()) {
                DebourDTO debourDTO = DebourDTO.builder()
                        .idDebourDTO(debour.getIdDebour())
                        .libelle(debour.getLibelle())
                        .montant(debour.getMontant())
                        .build();
                debourDTOList.add(debourDTO);
            }

            for (Honoraire honoraire:
                 invoice.getHonoraires()) {
                HonoraireDTO honoraireDTO = HonoraireDTO.builder()
                        .idHonoraireDTO(honoraire.getIdHonoraire())
                        .libelle(honoraire.getLibelle())
                        .montant(honoraire.getMontant())
                        .build();
                honoraireDTOList.add(honoraireDTO);
            }

            var clientDTO = ClientDTO.builder()
                    .name(invoice.getClient().getName())
                    .nif(invoice.getClient().getNif())
                    .address(invoice.getClient().getAddress())
                    .phoneNumber(invoice.getClient().getPhoneNumber())
                    .rccm(invoice.getClient().getRccm())
                    .email(invoice.getClient().getEmail())
                    .build();

            InvoiceDTO invoiceDTO = InvoiceDTO.builder()
                    .idInvoice(invoice.getIdInvoice())
                    .invoiceNumber(invoice.getInvoiceNumber())
                    .description(invoice.getDescription())
                    .tva(invoice.getTva())
                    .totalTTC(invoice.getTotalTTC())
                    .rsps(invoice.getRsps())
                    .net(invoice.getNet())
                    .debours(debourDTOList)
                    .honoraires(honoraireDTOList)
                    .creationDate(invoice.getCreationDate())
                    .client(clientDTO)
                    .build();

            invoices.add(invoiceDTO);

        }
        
        return invoices;
    }

    @Override
    public List<InvoiceDTO> getInvoicesByClient(Long idClient) {
        List<InvoiceDTO> invoices = new ArrayList<>();

        Client client = clientRepo.findById(idClient).orElseThrow(
                (() -> new IllegalStateException(
                        "Client with id " + idClient + " does not exist")
                )
        );

        for (Invoice invoice :
                invoiceRepo.findByClient(client)) {
            List<DebourDTO> debourDTOList = new ArrayList<>();
            List<HonoraireDTO> honoraireDTOList = new ArrayList<>();

            for (Debour debour :
                    invoice.getDebours()) {
                DebourDTO debourDTO = DebourDTO.builder()
                        .idDebourDTO(debour.getIdDebour())
                        .libelle(debour.getLibelle())
                        .montant(debour.getMontant())
                        .build();
                debourDTOList.add(debourDTO);
            }

            for (Honoraire honoraire:
                    invoice.getHonoraires()) {
                HonoraireDTO honoraireDTO = HonoraireDTO.builder()
                        .idHonoraireDTO(honoraire.getIdHonoraire())
                        .libelle(honoraire.getLibelle())
                        .montant(honoraire.getMontant())
                        .build();
                honoraireDTOList.add(honoraireDTO);
            }

            var clientDTO = ClientDTO.builder()
                    .name(invoice.getClient().getName())
                    .nif(invoice.getClient().getNif())
                    .address(invoice.getClient().getAddress())
                    .phoneNumber(invoice.getClient().getPhoneNumber())
                    .rccm(invoice.getClient().getRccm())
                    .email(invoice.getClient().getEmail())
                    .build();

            InvoiceDTO invoiceDTO = InvoiceDTO.builder()
                    .idInvoice(invoice.getIdInvoice())
                    .invoiceNumber(invoice.getInvoiceNumber())
                    .description(invoice.getDescription())
                    .tva(invoice.getTva())
                    .totalTTC(invoice.getTotalTTC())
                    .rsps(invoice.getRsps())
                    .net(invoice.getNet())
                    .debours(debourDTOList)
                    .honoraires(honoraireDTOList)
                    .creationDate(invoice.getCreationDate())
                    .client(clientDTO)
                    .build();

            invoices.add(invoiceDTO);

        }


        return invoices;
    }

    @Override
    public List<Debour> saveDebours(List<DebourDTO> debourDTOList, Invoice invoice) {
        List<Debour> debours = new ArrayList<>();
        for (DebourDTO debourDTO:debourDTOList) {
            var debour = Debour.builder()
                    .invoice(invoice)
                    .libelle(debourDTO.getLibelle())
                    .montant(debourDTO.getMontant())
                    .build();
            debours.add(debour);
            debourRepo.save(debour);
        }
        return debours;
    }

    @Override
    public List<Honoraire> saveHonoraires(List<HonoraireDTO> honoraireDTOList, Invoice invoice) {
        List<Honoraire> honoraires = new ArrayList<>();
        for (HonoraireDTO honoraireDTO:honoraireDTOList) {
            var honoraire = Honoraire.builder()
                    .invoice(invoice)
                    .libelle(honoraireDTO.getLibelle())
                    .montant(honoraireDTO.getMontant())
                    .build();
            honoraires.add(honoraire);
            honoraireRepo.save(honoraire);
        }
        return honoraires;
    }
}
