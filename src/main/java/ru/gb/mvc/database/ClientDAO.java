package ru.gb.mvc.database;

import ru.gb.mvc.domain.Client;

public interface ClientDAO {
    Client addClient(Client client);
    Client getClientById(long id);
    Client updateClient(Client client);
    void deleteClientById(long id);
}
