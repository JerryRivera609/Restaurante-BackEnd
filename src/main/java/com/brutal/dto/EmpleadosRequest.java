package com.brutal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadosRequest {
    private String nombre;
    private String email;
    private String contrasenia;
    private String rol;
}
