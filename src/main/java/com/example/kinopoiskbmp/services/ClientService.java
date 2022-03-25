package com.example.kinopoiskbmp.services;

import com.example.kinopoiskbmp.model.Client;

public interface ClientService {

    Client addClient(Client client);
    boolean clientIsExist(String email);

}
