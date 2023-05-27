package com.shakiroye.exac.controller;

import com.shakiroye.exac.dto.ClientRequest;
import com.shakiroye.exac.dto.ClientResponse;
import com.shakiroye.exac.dto.RegisterResponse;
import com.shakiroye.exac.model.Client;
import com.shakiroye.exac.model.Invoice;
import com.shakiroye.exac.model.User;
import com.shakiroye.exac.repository.ClientRepo;
import com.shakiroye.exac.security.JwtService;
import com.shakiroye.exac.service.ClientService;
import com.shakiroye.exac.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    private final ClientService clientService;
    private final InvoiceService invoiceService;
    private final JwtService jwtService;
    private final ClientRepo clientRepo;

    @PostMapping
    public ResponseEntity<ClientResponse> saveClient(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody ClientRequest clientRequest
    ) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().
                path("/api/v1/client").toUriString());

//        if (clientRepo.existsByEmail(clientRequest.getEmail())) {
//            return new ResponseEntity(RegisterResponse.builder()
//                    .message("email '" + clientRequest.getEmail() + "' already taken")
//                    .build() ,
//                    HttpStatus.BAD_REQUEST);
//        }
//
//        final Pattern VALID_EMAIL_ADDRESS_REGEX =
//                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$" , Pattern.CASE_INSENSITIVE);
//
//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(clientRequest.getEmail());
//
//        if (!matcher.find()) {
//            return new ResponseEntity(RegisterResponse.builder()
//                    .message("Invalid Email")
//                    .build() , HttpStatus.BAD_REQUEST);
//        }

        String jwtToken = authorizationHeader.substring(7);
        final User user = jwtService.extractUser(jwtToken);

        return ResponseEntity.created(uri).body(clientService.saveClient(clientRequest, user));
    }

    @GetMapping("all")
    public ResponseEntity<List<Client>> getClients(){
        return ResponseEntity.ok().body(clientService.getClients());
    }

    @DeleteMapping("{clientId}")
    public ResponseEntity<?> deleteBook(@PathVariable("clientId") Long clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.ok().body("Deleted successfully !");
    }

}
