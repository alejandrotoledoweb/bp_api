package com.microservicios_cuentas_movimientos.cuenta.movimientos.service;

import com.microservicios_cuentas_movimientos.cuenta.movimientos.dto.ClientDto;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.dto.CuentaDto;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.exception.AccountNumberAlreadyExistsException;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.model.Cuenta;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.repository.CuentaRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final RestTemplate restTemplate;

    @Value("${clients.service.url}")
    private String clientsServiceUrl;

    public CuentaService(CuentaRepository cuentaRepository, RestTemplate restTemplate) {
        this.cuentaRepository = cuentaRepository;
        this.restTemplate = restTemplate;
    }


    public Cuenta createCuenta(CuentaDto cuentaDto) {

        if (cuentaRepository.existsByAccountNumber(cuentaDto.getAccountNumber()) ){
            throw new AccountNumberAlreadyExistsException("Cuenta with account number " + cuentaDto.getAccountNumber() + " already exists");
        }

        String url = clientsServiceUrl + "/clientes/" + cuentaDto.getClientId();
        ResponseEntity<ClientDto> response = restTemplate.getForEntity(url, ClientDto.class);


        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new RuntimeException("Client not found");
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setClientId(cuentaDto.getClientId());
        cuenta.setAccountNumber(cuentaDto.getAccountNumber());
        cuenta.setBalance(cuentaDto.getBalance());


        return cuentaRepository.save(cuenta);
    }


    public Cuenta getCuentaById(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta not found"));
    }


    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }


    public List<Cuenta> getCuentasByClientId(Long clientId) {
        return cuentaRepository.findByClientId(clientId);
    }


    public Cuenta updateCuenta(Long id, Cuenta updatedCuenta) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta not found"));

        cuenta.setAccountNumber(updatedCuenta.getAccountNumber());
        cuenta.setBalance(updatedCuenta.getBalance());
        return cuentaRepository.save(cuenta);
    }


    public void deleteCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta not found"));
        cuentaRepository.delete(cuenta);
    }
}


