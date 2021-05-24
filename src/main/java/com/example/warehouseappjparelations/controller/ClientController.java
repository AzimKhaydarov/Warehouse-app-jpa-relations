package com.example.warehouseappjparelations.controller;

import com.example.warehouseappjparelations.entity.Client;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.ClientRepository;
import com.example.warehouseappjparelations.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    ClientRepository clientRepository;

    @PostMapping
    public Result addClient(@RequestBody Client client) {
        Result result = clientService.addClientService(client);
        return result;
    }

    @GetMapping("/all")
    public List<Client> getClients() {
        List<Client> clients = clientService.getClients();
        return clients;
    }

    @GetMapping("/{id}")
    public Object getClient(@PathVariable Integer id) {
        Object result = clientService.getClient(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result editClient(@RequestBody Client client, @PathVariable Integer id) {
        Result result = clientService.editClientService(client, id);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteClient(@PathVariable Integer id) {
        Result result = clientService.deleteClient(id);
        return result;
    }
}
