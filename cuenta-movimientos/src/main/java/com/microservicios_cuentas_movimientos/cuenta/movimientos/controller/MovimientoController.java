package com.microservicios_cuentas_movimientos.cuenta.movimientos.controller;

import com.microservicios_cuentas_movimientos.cuenta.movimientos.dto.MovimientoDto;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.exception.SaldoNoDisponibleException;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.model.Movimiento;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Object> crearMovimiento(@RequestBody MovimientoDto movimientoDto) {
        try {
            Movimiento nuevoMovimiento = movimientoService.crearMovimiento(movimientoDto);
            return new ResponseEntity<>(nuevoMovimiento, HttpStatus.CREATED);
        } catch (SaldoNoDisponibleException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> obtenerMovimientos() {
        List<Movimiento> movimientos = movimientoService.obtenerMovimientos();
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtenerMovimientoPorId(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.obtenerMovimientoPorId(id);
        return new ResponseEntity<>(movimiento, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id) {
        movimientoService.eliminarMovimiento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

