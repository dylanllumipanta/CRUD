package com.krakedev.clientes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.clientes.entidades.Cliente;

@Service
public class ServicioCliente {

    private final List<Cliente> clientes = new ArrayList<>();

    public Cliente crearCliente(Cliente cliente) {
        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> consultarClientes() {
        return clientes;
    }

    public Cliente consultarCliente(String cedula) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente actualizarCliente(String cedula, Cliente clienteActualizado) {
        Cliente cliente = consultarCliente(cedula);

        if (cliente != null) {
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setApellido(clienteActualizado.getApellido());
            cliente.setEmail(clienteActualizado.getEmail());
        }

        return cliente;
    }

    public boolean eliminarCliente(String cedula) {
        Cliente cliente = consultarCliente(cedula);

        if (cliente != null) {
            clientes.remove(cliente);
            return true;
        }

        return false;
    }
}
