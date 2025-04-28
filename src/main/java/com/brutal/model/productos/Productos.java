package com.brutal.model.productos;

import com.brutal.generics.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Productos extends BaseEntity {

    private String nombre;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private Double precio;
    private String descripcion;
    private String img;
}
