package com.microservicios_clientes.clientes.controller;

import com.microservicios_clientes.clientes.dto.ClientDto;
import com.microservicios_clientes.clientes.models.Cliente;
import com.microservicios_clientes.clientes.service.ClientService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientDto createCliente(@RequestBody ClientDto clientDTO) {
        return clientService.saveCliente(clientDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clientService.deleteCliente(id);
    }

    @PutMapping("/{id}")
    public ClientDto updateCliente(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        return clientService.updateCliente(id, clientDto);
    }

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clientService.findAllClientes();
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Long id) {
        return clientService.findClienteById(id);
    }
}
