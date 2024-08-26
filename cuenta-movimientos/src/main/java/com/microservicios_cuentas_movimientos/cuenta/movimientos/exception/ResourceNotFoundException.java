package com.microservicios_cuentas_movimientos.cuenta.movimientos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3L;

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}

