package com.restaurante.service;

import com.restaurante.dto.ClienteRequestDTO;
import com.restaurante.dto.ClienteResponseDTO;
import com.restaurante.entity.Cliente;
import com.restaurante.exception.ResourceNotFoundException;
import com.restaurante.mapper.ClienteMapper;
import com.restaurante.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import com.restaurante.dto.HistorialConsumoDTO;
import com.restaurante.dto.PedidoResponseDTO;
import com.restaurante.entity.Pedido;
import com.restaurante.mapper.PedidoMapper;
import com.restaurante.repository.PedidoRepository;
import com.restaurante.repository.DetallePedidoRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;

    public List<ClienteResponseDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteMapper::toDTO)
                .toList();
    }

    public ClienteResponseDTO obtenerClientePorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        return ClienteMapper.toDTO(cliente);
    }

    public ClienteResponseDTO crearCliente(ClienteRequestDTO dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        return ClienteMapper.toDTO(clienteRepository.save(cliente));
    }

    public ClienteResponseDTO actualizarCliente(Integer id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreo(dto.getCorreo());
        cliente.setFechaNacimiento(dto.getFechaNacimiento());
        cliente.setPreferencias(dto.getPreferencias());
        cliente.setAlergias(dto.getAlergias());
        cliente.setEstado(dto.getEstado() != null ? dto.getEstado() : cliente.getEstado());

        return ClienteMapper.toDTO(clienteRepository.save(cliente));
    }

    public void eliminarCliente(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        cliente.setEstado(false);
        clienteRepository.save(cliente);
    }

    public HistorialConsumoDTO obtenerHistorialConsumo(Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        List<Pedido> pedidos = pedidoRepository.findByClienteIdCliente(idCliente);

        BigDecimal totalGastado = pedidos.stream()
                .map(Pedido::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Long cantidadPedidos = (long) pedidos.size();

        BigDecimal ticketPromedio = cantidadPedidos > 0
                ? totalGastado.divide(BigDecimal.valueOf(cantidadPedidos), 2, java.math.RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        LocalDateTime ultimaVisita = pedidos.stream()
                .map(p -> LocalDateTime.of(p.getFecha(), p.getHora()))
                .max(LocalDateTime::compareTo)
                .orElse(null);

        List<PedidoResponseDTO> pedidosDTO = pedidos.stream()
                .map(pedido -> PedidoMapper.toDTO(
                        pedido,
                        detallePedidoRepository.findByPedidoIdPedido(pedido.getIdPedido())
                ))
                .toList();

        return HistorialConsumoDTO.builder()
                .idCliente(cliente.getIdCliente())
                .nombreCliente(cliente.getNombre() + " " + (cliente.getApellido() != null ? cliente.getApellido() : ""))
                .cantidadPedidos(cantidadPedidos)
                .totalGastado(totalGastado)
                .ticketPromedio(ticketPromedio)
                .ultimaVisita(ultimaVisita)
                .pedidos(pedidosDTO)
                .build();
    }

    public ClienteResponseDTO registrarOActualizarCliente(ClienteRequestDTO dto) {

        Cliente cliente = clienteRepository.findByTelefono(dto.getTelefono())
                .orElse(null);

        if (cliente == null && dto.getCorreo() != null && !dto.getCorreo().isBlank()) {
            cliente = clienteRepository.findByCorreo(dto.getCorreo())
                    .orElse(null);
        }

        if (cliente == null) {
            Cliente nuevoCliente = ClienteMapper.toEntity(dto);
            return ClienteMapper.toDTO(clienteRepository.save(nuevoCliente));
        }

        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setCorreo(dto.getCorreo());
        cliente.setFechaNacimiento(dto.getFechaNacimiento());
        cliente.setPreferencias(dto.getPreferencias());
        cliente.setAlergias(dto.getAlergias());
        cliente.setEstado(true);

        return ClienteMapper.toDTO(clienteRepository.save(cliente));
    }
}
