package com.brutal.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
@Data
public class FacturaResponse{
    private Long id;
    private OffsetDateTime fecha;
    private Double precioTotal;
    private String estado;
    private String medioDePago;
    private PedidoInfo pedidoInfo;

    @Data
    public static class PedidoInfo{
        private Long idPedido;
        private Integer numeroMesa;
        private List<DetallePedidoInfo> detalles;
    }
    @Data
    public static class DetallePedidoInfo{
        private String nombreCompleto;
        private Integer cantidad;
        private Double precio;
    }
}

