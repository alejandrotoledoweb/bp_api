package com.microservicios_cuentas_movimientos.cuenta.movimientos.exception;

import java.io.Serial;

public class AccountNumberAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2L;

    public AccountNumberAlreadyExistsException(String message) {
        super(message);
    }
}
