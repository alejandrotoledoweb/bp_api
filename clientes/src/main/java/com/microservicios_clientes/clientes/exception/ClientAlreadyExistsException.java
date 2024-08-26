package com.microservicios_clientes.clientes.exception;

import java.io.Serial;

public class ClientAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
