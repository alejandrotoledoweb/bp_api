package com.microservicios_cuentas_movimientos.cuenta.movimientos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String type; // "DEBITO" o "CREDITO"
    private Date date;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

}
