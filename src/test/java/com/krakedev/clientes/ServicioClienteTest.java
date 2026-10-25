package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.services.ServicioCliente;

public class ServicioClienteTest {

    private ServicioCliente servicio;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        servicio = new ServicioCliente();
        cliente = new Cliente("1712345678", "Dylan", "Llumipanta");
    }

    @Test
    public void testCrear() {
        Cliente resultado = servicio.crear(cliente);

        assertNotNull(resultado);
        assertEquals("1712345678", resultado.getCedula());
        assertEquals("Dylan", resultado.getNombre());
        assertEquals("Llumipanta", resultado.getApellido());
    }

    @Test
    public void testCrearClienteDuplicado() {
        servicio.crear(cliente);

        Cliente duplicado = new Cliente(
            "1712345678", "Carlos", "Perez"
        );

        Cliente resultado = servicio.crear(duplicado);

        assertNull(resultado);
        assertEquals(1, servicio.listar().size());
    }

    @Test
    public void testBuscarPorCedula() {
        servicio.crear(cliente);

        Cliente resultado = servicio.buscarPorCedula("1712345678");

        assertNotNull(resultado);
        assertEquals("Dylan", resultado.getNombre());
    }

    @Test
    public void testBuscarPorCedulaInexistente() {
        Cliente resultado = servicio.buscarPorCedula("0000000000");

        assertNull(resultado);
    }

    @Test
    public void testListar() {
        servicio.crear(cliente);

        Cliente cliente2 = new Cliente(
            "1723456789", "Maria", "Lopez"
        );

        servicio.crear(cliente2);

        List<Cliente> resultado = servicio.listar();

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(cliente));
        assertTrue(resultado.contains(cliente2));
    }

    @Test
    public void testActualizar() {
        servicio.crear(cliente);

        Cliente datosActualizados = new Cliente(
            "1712345678", "Daniel", "Garcia"
        );

        Cliente resultado = servicio.actualizar(
            "1712345678", datosActualizados
        );

        assertNotNull(resultado);
        assertEquals("Daniel", resultado.getNombre());
        assertEquals("Garcia", resultado.getApellido());
        assertEquals("1712345678", resultado.getCedula());
    }

    @Test
    public void testActualizarClienteInexistente() {
        Cliente datosActualizados = new Cliente(
            "0000000000", "Pedro", "Lopez"
        );

        Cliente resultado = servicio.actualizar(
            "0000000000", datosActualizados
        );

        assertNull(resultado);
    }

    @Test
    public void testEliminar() {
        servicio.crear(cliente);

        boolean resultado = servicio.eliminar("1712345678");

        assertTrue(resultado);
        assertNull(servicio.buscarPorCedula("1712345678"));
        assertEquals(0, servicio.listar().size());
    }

    @Test
    public void testEliminarClienteInexistente() {
        boolean resultado = servicio.eliminar("0000000000");

        assertFalse(resultado);
    }
}