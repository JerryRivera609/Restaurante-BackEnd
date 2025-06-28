package com.brutal.dto;

import lombok.Data;

@Data
public class ProductoRequest {
    private String nombre;
    private String tipo;
    private Double precio;
    private String resumen;
    private String img;
}
