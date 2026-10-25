package com.krakedev.clientes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.services.ServicioCliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ServicioCliente servicioCliente;

    public ClienteController(ServicioCliente servicioCliente) {
        this.servicioCliente = servicioCliente;
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente creado = servicioCliente.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> consultarClientes() {
        return ResponseEntity.ok(servicioCliente.consultarClientes());
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<Cliente> consultarCliente(@PathVariable String cedula) {
        Cliente cliente = servicioCliente.consultarCliente(cedula);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{cedula}")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable String cedula,
            @RequestBody Cliente cliente) {

        Cliente actualizado = servicioCliente.actualizarCliente(cedula, cliente);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String cedula) {
        boolean eliminado = servicioCliente.eliminarCliente(cedula);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}