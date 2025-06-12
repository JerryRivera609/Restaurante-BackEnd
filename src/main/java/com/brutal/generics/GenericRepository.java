package com.brutal.generics;

import com.brutal.dto.DetallePedidoResponse;
import com.brutal.model.detallePedido.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository <T extends BaseEntity, Long> extends JpaRepository <T, Long> {
}
