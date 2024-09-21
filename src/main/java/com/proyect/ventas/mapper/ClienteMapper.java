package com.proyect.ventas.mapper;

import com.proyect.ventas.dto.ClienteDTO;
import com.proyect.ventas.models.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toClienteDTO(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setRfc(cliente.getRfc());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setCelular(cliente.getCelular());
        clienteDTO.setDireccion(cliente.getDireccion());
        clienteDTO.setFechaCreacion(cliente.getFechaCreacion());
        clienteDTO.setFechaActualizacion(cliente.getFechaActualizacion());
        clienteDTO.setActivo(cliente.isActivo());
        return clienteDTO;
    }

    public Cliente toCLienteEntity(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setRfc(clienteDTO.getRfc());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setCelular(clienteDTO.getCelular());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setActivo(clienteDTO.isActivo());
        return cliente;
    }
}
