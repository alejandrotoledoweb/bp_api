package com.microservicios_cuentas_movimientos.cuenta.movimientos.repository;

import com.microservicios_cuentas_movimientos.cuenta.movimientos.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByClientId(Long clientId);

    boolean existsByAccountNumber(String accountNumber);
    Optional<Cuenta> findByAccountNumber(String accountNumber);
}
