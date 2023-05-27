package com.shakiroye.exac.service;

import com.shakiroye.exac.dto.ClientRequest;
import com.shakiroye.exac.dto.ClientResponse;
import com.shakiroye.exac.model.Client;
import com.shakiroye.exac.model.Invoice;
import com.shakiroye.exac.model.User;

import java.util.List;

public interface ClientService {

    ClientResponse saveClient(ClientRequest clientRequest, User user);
    ClientResponse updateClient(Long idClient, ClientRequest clientRequest);
    List<Client> getClients();
    ClientResponse getClient(Long idClient);
    void deleteClient(Long idClient);
}
