package com.brutal.model.facturas;


import com.brutal.generics.BaseEntity;
import com.brutal.model.pedidos.Pedidos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "facturas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Factura extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "id_pedido")
    private Pedidos pedido;
    private Double total;

    @CreationTimestamp
    @Column(name = "fecha_hora", columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    private EstadoFactura estado;

    @Column(name = "medio_de_pago")
    @Enumerated(EnumType.STRING)
    private MedioDePago medioDePago;


}
