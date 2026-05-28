package com.restaurante.service;

import com.restaurante.dto.DetallePedidoRequestDTO;
import com.restaurante.dto.PedidoRequestDTO;
import com.restaurante.dto.PedidoResponseDTO;
import com.restaurante.entity.*;
import com.restaurante.mapper.PedidoMapper;
import com.restaurante.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.restaurante.exception.ResourceNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final MesaRepository mesaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(pedido -> PedidoMapper.toDTO(
                        pedido,
                        detallePedidoRepository.findByPedidoIdPedido(pedido.getIdPedido())
                ))
                .toList();
    }

    public List<PedidoResponseDTO> listarPedidosPorEstado(String estado) {
        return pedidoRepository.findByEstado(estado)
                .stream()
                .map(pedido -> PedidoMapper.toDTO(
                        pedido,
                        detallePedidoRepository.findByPedidoIdPedido(pedido.getIdPedido())
                ))
                .toList();
    }

    public PedidoResponseDTO obtenerPedidoPorId(Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        List<DetallePedido> detalles = detallePedidoRepository.findByPedidoIdPedido(id);

        return PedidoMapper.toDTO(pedido, detalles);
    }

    public PedidoResponseDTO crearPedido(PedidoRequestDTO dto) {
        Mesa mesa = mesaRepository.findById(dto.getIdMesa())
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada"));

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Cliente cliente = null;

        if (dto.getIdCliente() != null) {
            cliente = clienteRepository.findById(dto.getIdCliente())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        }

        Pedido pedido = Pedido.builder()
                .fecha(LocalDate.now())
                .hora(LocalTime.now())
                .estado("Pendiente")
                .observacion(dto.getObservacion())
                .total(BigDecimal.ZERO)
                .mesa(mesa)
                .usuario(usuario)
                .cliente(cliente)
                .build();

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        for (DetallePedidoRequestDTO detalleDTO : dto.getDetalles()) {
            Producto producto = productoRepository.findById(detalleDTO.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

            DetallePedido detalle = DetallePedido.builder()
                    .pedido(pedidoGuardado)
                    .producto(producto)
                    .cantidad(detalleDTO.getCantidad())
                    .precioUnitario(producto.getPrecio())
                    .build();

            detallePedidoRepository.save(detalle);
        }

        Pedido pedidoActualizado = pedidoRepository.findById(pedidoGuardado.getIdPedido())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        List<DetallePedido> detalles = detallePedidoRepository.findByPedidoIdPedido(pedidoActualizado.getIdPedido());

        return PedidoMapper.toDTO(pedidoActualizado, detalles);
    }

    public PedidoResponseDTO cambiarEstado(Integer idPedido, String estado) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        pedido.setEstado(estado);

        Pedido pedidoActualizado = pedidoRepository.save(pedido);

        List<DetallePedido> detalles = detallePedidoRepository.findByPedidoIdPedido(idPedido);

        return PedidoMapper.toDTO(pedidoActualizado, detalles);
    }
}