package com.proyect.ventas.service;

import com.proyect.ventas.dto.ClienteDTO;
import com.proyect.ventas.mapper.ClienteMapper;
import com.proyect.ventas.models.Cliente;
import com.proyect.ventas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public List<ClienteDTO> ObtenerTodosLosClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::toClienteDTO)
                .collect(Collectors.toList());
    }

    // Obtener solo los clientes activos
    public List<ClienteDTO> obtenerClientesActivos() {
        List<Cliente> clientes = clienteRepository.findByActivoTrue();
        return clientes.stream()
                .map(clienteMapper::toClienteDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO obtenerClientePorId(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(clienteMapper::toClienteDTO).orElse(null);
    }

    public ClienteDTO crearCliente(ClienteDTO clienteDTO){
        if(clienteRepository.existsByRfc(clienteDTO.getRfc())){
            throw new RuntimeException("Ya existe un cliente registrado con este rfc");
        }

        Cliente cliente = clienteMapper.toCLienteEntity(clienteDTO);
        Cliente nuevoCliente = clienteRepository.save(cliente);
        return clienteMapper.toClienteDTO(nuevoCliente);
    }

    public ClienteDTO actualizaCliente(Long id, ClienteDTO clienteDTO){
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if(clienteExistente.isPresent()){
            Cliente cliente = clienteExistente.get();

            //Validamos que el rfc al actualizar no sea uno ya existente y aparte que sea diferente al que acaban de ingresar
            if (!cliente.getRfc().equals(clienteDTO.getRfc()) && clienteRepository.existsByRfc(clienteDTO.getRfc())){
                throw new RuntimeException("El RFC ingresado ya existe");
            }

            cliente.setNombre(clienteDTO.getNombre());
            cliente.setRfc(clienteDTO.getRfc());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setTelefono(clienteDTO.getTelefono());
            cliente.setCelular(clienteDTO.getCelular());
            cliente.setDireccion(clienteDTO.getDireccion());
            cliente.setFechaActualizacion(LocalDate.now());
            Cliente clienteActualizado = clienteRepository.save(cliente);
            return clienteMapper.toClienteDTO(clienteActualizado);
        } else {
            return null;
        }
    }

    public void desactivarCliente(Long id){
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()){
            Cliente cliente = clienteExistente.get();
            cliente.setActivo(false);
            clienteRepository.save(cliente);
        }
    }
}
