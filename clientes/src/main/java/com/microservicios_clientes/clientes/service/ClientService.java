package com.microservicios_clientes.clientes.service;

import com.microservicios_clientes.clientes.dto.ClientDto;
import com.microservicios_clientes.clientes.exception.ClientAlreadyExistsException;
import com.microservicios_clientes.clientes.models.Cliente;
import com.microservicios_clientes.clientes.repository.ClientRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDto saveCliente(ClientDto clientDto) {
        if (clientRepository.existsByEmail(clientDto.getEmail())) {
            throw new ClientAlreadyExistsException("Client with email " + clientDto.getEmail() + " already exists");
        }

        Cliente cliente = new Cliente();
        cliente.setName(clientDto.getName());
        cliente.setEmail(clientDto.getEmail());
        cliente = clientRepository.save(cliente);
        clientDto.setId(cliente.getId());
        return clientDto;
    }

    public void deleteCliente(Long id) {
        clientRepository.deleteById(id);
    }

    public ClientDto updateCliente(Long id, ClientDto clientDto) {
        if (clientRepository.existsByEmailAndIdNot(clientDto.getEmail(), id)) {
            throw new ClientAlreadyExistsException("Client with email " + clientDto.getEmail() + " already exists");
        }

        Cliente cliente = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setName(clientDto.getName());
        cliente.setEmail(clientDto.getEmail());
        clientRepository.save(cliente);
        return clientDto;
    }

    public List<Cliente> findAllClientes() {
        return clientRepository.findAll();
    }

    public Cliente findClienteById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}
