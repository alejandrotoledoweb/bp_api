package com.microservicios_cuentas_movimientos.cuenta.movimientos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;
    @Column(unique = true)
    private String accountNumber;
    private Double balance;


}
