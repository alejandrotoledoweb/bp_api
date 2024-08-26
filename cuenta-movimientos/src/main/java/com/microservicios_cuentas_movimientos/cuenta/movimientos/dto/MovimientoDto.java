package com.microservicios_cuentas_movimientos.cuenta.movimientos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class MovimientoDto {

    @NotNull(message = "el número de cuenta no puede estar vacio")
    private String accountNumber;
    @NotNull(message = "el monto no puede estar vacio")
    private Double amount;
}
