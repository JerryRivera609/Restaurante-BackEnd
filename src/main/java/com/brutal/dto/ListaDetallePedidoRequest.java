package com.brutal.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class ListaDetallePedidoRequest {
    private Long IdPedido;
    private List<DetalleItem> items;

    @Data
    @NoArgsConstructor
    public static class DetalleItem {
        private Long idProducto;
        private Integer cantidad;
        private Double precio;
    }
}
