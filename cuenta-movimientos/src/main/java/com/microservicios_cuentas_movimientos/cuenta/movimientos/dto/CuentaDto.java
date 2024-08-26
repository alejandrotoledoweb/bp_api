package com.microservicios_cuentas_movimientos.cuenta.movimientos.dto;

import lombok.Data;

@Data
public class CuentaDto {

    private Long clientId;
    private String accountNumber;
    private Double balance;
}
