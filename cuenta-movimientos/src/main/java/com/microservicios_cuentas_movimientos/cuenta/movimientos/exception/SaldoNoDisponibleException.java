package com.microservicios_cuentas_movimientos.cuenta.movimientos.exception;

public class SaldoNoDisponibleException extends RuntimeException {
    public SaldoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}

