package com.brutal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaRequest {
    private Long id;
    private OffsetDateTime fecha;
    private Double precioTotal;
    private Long idPedido;
    private String estado;
    private String medioDePago;
}
