package com.microservicios_clientes.clientes.entity;

import com.microservicios_clientes.clientes.models.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testClienteCreation() {
        // Crear un cliente
        Cliente cliente = new Cliente();
        cliente.setName("John Doe");
        cliente.setEmail("john.doe@example.com");

        // Verificar que los valores se setean correctamente
        assertEquals("John Doe", cliente.getName());
        assertEquals("john.doe@example.com", cliente.getEmail());
    }

    @Test
    public void testClienteEquality() {
        // Crear dos clientes con los mismos datos
        Cliente cliente1 = new Cliente();
        cliente1.setName("John Doe");
        cliente1.setEmail("john.doe@example.com");

        Cliente cliente2 = new Cliente();
        cliente2.setName("John Doe");
        cliente2.setEmail("john.doe@example.com");

        // Verificar que no son iguales (porque tienen IDs diferentes)
        assertNotEquals(cliente1, cliente2);
    }

    @Test
    public void testClienteDefaultIdIsNull() {
        // Crear un cliente y verificar que el ID es null al principio
        Cliente cliente = new Cliente();
        assertNull(cliente.getId());
    }
}
