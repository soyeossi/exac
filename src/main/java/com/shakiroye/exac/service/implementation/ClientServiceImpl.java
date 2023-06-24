package com.shakiroye.exac.service.implementation;

import com.shakiroye.exac.dto.ClientRequest;
import com.shakiroye.exac.dto.ClientResponse;
import com.shakiroye.exac.model.Client;
import com.shakiroye.exac.model.Invoice;
import com.shakiroye.exac.model.User;
import com.shakiroye.exac.repository.ClientRepo;
import com.shakiroye.exac.repository.InvoiceRepo;
import com.shakiroye.exac.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;
    private final InvoiceRepo invoiceRepo;

    @Override
    public ClientResponse saveClient(ClientRequest clientRequest, User user) {

        var client = Client.builder()
                .user(user)
                .name(clientRequest.getName())
                .acronym(clientRequest.getAcronym())
                .email(clientRequest.getEmail())
                .nif(clientRequest.getNif())
                .address(clientRequest.getAddress())
                .phoneNumber(clientRequest.getPhoneNumber())
                .rccm(clientRequest.getRccm())
                .build();
        clientRepo.save(client);

        Map clientInfo = new HashMap<>();
        clientInfo.put("idUser" , client.getIdClient());
        clientInfo.put("name" , client.getName());
        clientInfo.put("acronym" , client.getAcronym());
        clientInfo.put("email" , client.getEmail());
        clientInfo.put("nif" , client.getNif());
        clientInfo.put("rccm" , client.getRccm());
        clientInfo.put("address" , client.getAddress());
        clientInfo.put("phoneNumber" , client.getPhoneNumber());

        return ClientResponse.builder()
                .client(clientInfo)
                .message("Client added successfully")
                .build();
    }

    @Override
    public ClientResponse updateClient(Long idClient , ClientRequest clientRequest) {
        Client client = clientRepo.findById(idClient).orElseThrow(
                (() -> new IllegalStateException(
                        "Client with id " + idClient + " does not exist")
                )
        );

        client.setAddress(clientRequest.getAddress());
        client.setEmail(clientRequest.getEmail());
        client.setName(clientRequest.getName());
        client.setAcronym(clientRequest.getAcronym());
        client.setNif(clientRequest.getNif());
        client.setRccm(clientRequest.getRccm());
        client.setPhoneNumber(clientRequest.getPhoneNumber());
        clientRepo.save(client);

        Map clientInfo = new HashMap<>();
        clientInfo.put("idUser" , client.getIdClient());
        clientInfo.put("name" , client.getName());
        clientInfo.put("acronym" , client.getAcronym());
        clientInfo.put("email" , client.getEmail());
        clientInfo.put("nif" , client.getNif());
        clientInfo.put("rccm" , client.getRccm());
        clientInfo.put("address" , client.getAddress());
        clientInfo.put("phoneNumber" , client.getPhoneNumber());

        return ClientResponse.builder()
                .client(clientInfo)
                .message("Client updated successfully !")
                .build();
    }

    @Override
    public List<Client> getClients() {
        return clientRepo.findAll();
    }

    @Override
    public ClientResponse getClient(Long idClient) {
        Client client = clientRepo.findById(idClient).orElseThrow(
                (() -> new IllegalStateException(
                        "Client with id " + idClient + " does not exist")
                )
        );

        Map clientInfo = new HashMap<>();
        clientInfo.put("idUser" , client.getIdClient());
        clientInfo.put("name" , client.getName());
        clientInfo.put("acronym" , client.getAcronym());
        clientInfo.put("email" , client.getEmail());
        clientInfo.put("nif" , client.getNif());
        clientInfo.put("rccm" , client.getRccm());
        clientInfo.put("address" , client.getAddress());
        clientInfo.put("phoneNumber" , client.getPhoneNumber());

        return ClientResponse.builder()
                .client(clientInfo)
                .message("Found !")
                .build();
    }

    @Override
    public void deleteClient(Long idClient) {
        clientRepo.deleteById(idClient);
    }
}
