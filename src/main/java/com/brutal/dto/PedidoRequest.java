package com.brutal.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    @NonNull
    private Long idEmpleado;
    @NonNull
    private Long idMesa;
}
