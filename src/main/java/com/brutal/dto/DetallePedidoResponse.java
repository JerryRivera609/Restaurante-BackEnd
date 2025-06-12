package com.brutal.dto;
//Esta clase enviare los pedidos a cada pantalla de cada empleado, COCINA, FRIOS, BAR


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoResponse {
    private Long id;
    private String nombre;
    private String img;
    private Integer cantidad;
    private String tipo;
    private String estado;

}
