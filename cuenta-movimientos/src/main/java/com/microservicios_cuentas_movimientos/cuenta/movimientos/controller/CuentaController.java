package com.microservicios_cuentas_movimientos.cuenta.movimientos.controller;

import com.microservicios_cuentas_movimientos.cuenta.movimientos.dto.CuentaDto;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.model.Cuenta;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.service.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }


    @PostMapping
    public ResponseEntity<?> createCuenta(@RequestBody CuentaDto cuentaDto) {
        try {
            Cuenta createdCuenta = cuentaService.createCuenta(cuentaDto);
            return new ResponseEntity<>(createdCuenta, HttpStatus.CREATED);
        } catch (RuntimeException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaService.getCuentaById(id));
    }


    @GetMapping
    public ResponseEntity<List<Cuenta>> getAllCuentas() {
        return ResponseEntity.ok(cuentaService.getAllCuentas());
    }


    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Cuenta>> getCuentasByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(cuentaService.getCuentasByClientId(clientId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        try {
            Cuenta updatedCuenta = cuentaService.updateCuenta(id, cuenta);
            return new ResponseEntity<>(updatedCuenta, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable Long id) {
        try {
            cuentaService.deleteCuenta(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}