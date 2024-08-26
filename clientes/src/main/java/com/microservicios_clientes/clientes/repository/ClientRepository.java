package com.microservicios_clientes.clientes.repository;


import com.microservicios_clientes.clientes.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByEmail(String email);
}
