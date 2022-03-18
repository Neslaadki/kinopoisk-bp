package com.example.kinopoiskbmp.services;


import com.example.kinopoiskbmp.entities.Client;
import com.example.kinopoiskbmp.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public boolean clientIsExist(String email){
        return clientRepository.findByEmail(email) != null;
    }

    public Client addClient(Client client){
        if (!clientIsExist(client.getEmail()))
            return clientRepository.save(client);
        else
            return clientRepository.findByEmail(client.getEmail());
    }

}
