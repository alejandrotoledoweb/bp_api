package com.microservicios_cuentas_movimientos.cuenta.movimientos.repository;

import com.microservicios_cuentas_movimientos.cuenta.movimientos.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaId(Long cuentaId);
}
