package com.example.kinopoiskbmp.repositories;

import com.example.kinopoiskbmp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>{
    Client findByEmail(String email);
}
