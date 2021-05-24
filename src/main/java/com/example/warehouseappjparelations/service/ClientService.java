package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.Client;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Result addClientService(Client client) {
        boolean existsByName = clientRepository.existsByName(client.getName());
        if (existsByName)
            return new Result("Client  already exists!", false);
        Client saved = clientRepository.save(client);
        return new Result("Client  added successfully!", true);
    }

    public Result editClientService(Client client, Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        Client client1 = optionalClient.get();
        if (!optionalClient.isPresent()) return new Result("The client  not found!", false);
        client1.setName(client.getName());
        client1.setActive(client.isActive());
        clientRepository.save(client1);
        return new Result("Client edited successfully!", true);

    }
    public List<Client> getClients() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }
    public Object getClient(@PathVariable Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(!optionalClient.isPresent()) return new Result("The client  with current id not found", false);
        return optionalClient.get();}

    public Result deleteClient(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) return new Result("The client not found!", false);
        Client client1 = optionalClient.get();
        clientRepository.delete(client1);
        return new Result("The client deleted successfully!", true);
    }
}
