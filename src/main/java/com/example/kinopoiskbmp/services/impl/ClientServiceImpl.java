package com.example.kinopoiskbmp.services.impl;


import com.example.kinopoiskbmp.model.Client;
import com.example.kinopoiskbmp.repositories.ClientRepository;
import com.example.kinopoiskbmp.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public boolean clientIsExist(String email){
        return clientRepository.findByEmail(email) != null;
    }

    @Override
    public Client addClient(Client client){
        System.out.println(client.toString());
        if (!clientIsExist(client.getEmail())){
            Client client1 = clientRepository.save(client);
            System.out.println(client1);
            System.out.println("Гавно");
            return client1;
        }
        else
            return clientRepository.findByEmail(client.getEmail());
    }

}
