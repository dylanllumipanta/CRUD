package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.services.ServicioCliente;

class ServicioClienteTest {

    private ServicioCliente servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioCliente();
    }

    @Test
    void testCrearYConsultarCliente() {
        Cliente cliente = new Cliente(
                "1234567890",
                "Dylan",
                "Llumipanta",
                "dylan@example.com"
        );

        servicio.crearCliente(cliente);

        Cliente encontrado = servicio.consultarCliente("1234567890");

        assertNotNull(encontrado);
        assertEquals("dylan@example.com", encontrado.getEmail());
    }

    @Test
    void testActualizarEmail() {
        Cliente cliente = new Cliente(
                "1234567890",
                "Dylan",
                "Llumipanta",
                "dylan@example.com"
        );

        servicio.crearCliente(cliente);

        Cliente actualizado = new Cliente(
                "1234567890",
                "Dylan",
                "Llumipanta",
                "nuevo@example.com"
        );

        servicio.actualizarCliente("1234567890", actualizado);

        Cliente encontrado = servicio.consultarCliente("1234567890");

        assertEquals("nuevo@example.com", encontrado.getEmail());
    }

    @Test
    void testEliminarCliente() {
        Cliente cliente = new Cliente(
                "1234567890",
                "Dylan",
                "Llumipanta",
                "dylan@example.com"
        );

        servicio.crearCliente(cliente);

        boolean eliminado = servicio.eliminarCliente("1234567890");

        assertTrue(eliminado);
        assertNull(servicio.consultarCliente("1234567890"));
    }

    @Test
    void testConsultarClienteInexistente() {
        assertNull(servicio.consultarCliente("0000000000"));
    }
}