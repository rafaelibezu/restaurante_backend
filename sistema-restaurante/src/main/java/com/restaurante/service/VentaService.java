package com.restaurante.service;

import com.restaurante.dto.VentaRequestDTO;
import com.restaurante.dto.VentaResponseDTO;
import com.restaurante.entity.Pedido;
import com.restaurante.entity.Usuario;
import com.restaurante.entity.Venta;
import com.restaurante.mapper.VentaMapper;
import com.restaurante.repository.PedidoRepository;
import com.restaurante.repository.UsuarioRepository;
import com.restaurante.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import com.restaurante.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<VentaResponseDTO> listarVentas() {
        return ventaRepository.findAll()
                .stream()
                .map(VentaMapper::toDTO)
                .toList();
    }

    public VentaResponseDTO obtenerVentaPorId(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada"));

        return VentaMapper.toDTO(venta);
    }

    public VentaResponseDTO registrarVenta(VentaRequestDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Venta venta = Venta.builder()
                .fecha(LocalDate.now())
                .total(pedido.getTotal())
                .metodoPago(dto.getMetodoPago())
                .estado("Pagado")
                .pedido(pedido)
                .usuario(usuario)
                .build();

        return VentaMapper.toDTO(ventaRepository.save(venta));
    }
}