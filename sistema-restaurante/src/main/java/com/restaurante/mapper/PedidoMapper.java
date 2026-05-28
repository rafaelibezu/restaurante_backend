package com.restaurante.mapper;

import com.restaurante.dto.DetallePedidoResponseDTO;
import com.restaurante.dto.PedidoResponseDTO;
import com.restaurante.entity.DetallePedido;
import com.restaurante.entity.Pedido;

import java.util.List;

public class PedidoMapper {

    public static DetallePedidoResponseDTO detalleToDTO(DetallePedido detalle) {
        return DetallePedidoResponseDTO.builder()
                .idDetalle(detalle.getIdDetalle())
                .idProducto(detalle.getProducto().getIdProducto())
                .nombreProducto(detalle.getProducto().getNombre())
                .cantidad(detalle.getCantidad())
                .precioUnitario(detalle.getPrecioUnitario())
                .subtotal(detalle.getSubtotal())
                .build();
    }

    public static PedidoResponseDTO toDTO(Pedido pedido, List<DetallePedido> detalles) {
        return PedidoResponseDTO.builder()
                .idPedido(pedido.getIdPedido())
                .fecha(pedido.getFecha())
                .hora(pedido.getHora())
                .estado(pedido.getEstado())
                .observacion(pedido.getObservacion())
                .total(pedido.getTotal())
                .idMesa(pedido.getMesa().getIdMesa())
                .numeroMesa(pedido.getMesa().getNumero())
                .idUsuario(pedido.getUsuario().getIdUsuario())
                .nombreUsuario(pedido.getUsuario().getNombre())
                .idCliente(
                        pedido.getCliente() != null
                                ? pedido.getCliente().getIdCliente()
                                : null
                )

                .nombreCliente(
                        pedido.getCliente() != null
                                ? pedido.getCliente().getNombre() + " " +
                                (pedido.getCliente().getApellido() != null
                                        ? pedido.getCliente().getApellido()
                                        : "")
                                : "Cliente no registrado"
                )
                .detalles(detalles.stream()
                        .map(PedidoMapper::detalleToDTO)
                        .toList())
                .build();
    }
}