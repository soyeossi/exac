package com.shakiroye.exac.repository;

import com.shakiroye.exac.model.Client;
import com.shakiroye.exac.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);
    Boolean existsByEmail(String email);
}
