package com.brutal.model.pedidos;

import com.brutal.generics.BaseEntity;
import com.brutal.model.mesas.Mesas;
import com.brutal.model.empleado.Empleado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Pedidos extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "id_mesero")
    private Empleado empleado;
    @ManyToOne
    @JoinColumn(name = "id_mesa")
    private Mesas mesa;
    @CreationTimestamp
    @Column(name = "fecha_hora", columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime fechaHora;
    @Enumerated(EnumType.STRING)
    private Estado estado;
}
