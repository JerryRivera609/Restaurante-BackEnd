package com.brutal.model.detallePedido;

import com.brutal.generics.BaseEntity;
import com.brutal.model.mesas.Estado;
import com.brutal.model.pedidos.Pedidos;
import com.brutal.model.productos.Productos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalles_pedido")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DetallePedido extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedidos pedido;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Productos producto;
    private Integer cantidad;
    private Double precio;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

}
