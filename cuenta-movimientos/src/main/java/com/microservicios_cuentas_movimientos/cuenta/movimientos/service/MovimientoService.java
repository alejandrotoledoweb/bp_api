package com.microservicios_cuentas_movimientos.cuenta.movimientos.service;

import com.microservicios_cuentas_movimientos.cuenta.movimientos.dto.MovimientoDto;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.exception.ResourceNotFoundException;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.exception.SaldoNoDisponibleException;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.model.Cuenta;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.model.Movimiento;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.repository.CuentaRepository;
import com.microservicios_cuentas_movimientos.cuenta.movimientos.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Date;
import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public Movimiento crearMovimiento(MovimientoDto movimientoDto) throws SaldoNoDisponibleException {

        Cuenta cuenta = cuentaRepository.findByAccountNumber(movimientoDto.getAccountNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));

        Movimiento movimiento = new Movimiento();
        movimiento.setAmount(movimientoDto.getAmount());
        movimiento.setDate(new Date());
        movimiento.setCuenta(cuenta);


        if (movimientoDto.getAmount() < 0) {
            movimiento.setType("DEBITO");


            if (cuenta.getBalance() + movimientoDto.getAmount() < 0) {
                throw new SaldoNoDisponibleException("Saldo no disponible");
            }

            cuenta.setBalance(cuenta.getBalance() + movimientoDto.getAmount());
        } else {
            movimiento.setType("CREDITO");

            cuenta.setBalance(cuenta.getBalance() + movimientoDto.getAmount());
        }

        cuentaRepository.save(cuenta);

        return movimientoRepository.save(movimiento);
    }

    public List<Movimiento> obtenerMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento obtenerMovimientoPorId(Long id) {
        return movimientoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado"));
    }

    public void eliminarMovimiento(Long id) {
        if (!movimientoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Movimiento no encontrado");
        }
        movimientoRepository.deleteById(id);
    }
}

