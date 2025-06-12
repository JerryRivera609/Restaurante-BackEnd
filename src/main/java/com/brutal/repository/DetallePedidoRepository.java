package com.brutal.repository;

import com.brutal.generics.GenericRepository;
import com.brutal.model.detallePedido.DetallePedido;
import com.brutal.model.detallePedido.EstadoPedido;
import com.brutal.service.DetallePedidoService;

import java.util.List;

public interface DetallePedidoRepository extends GenericRepository <DetallePedido, Long> {
    List<DetallePedido> findByEstado(EstadoPedido estado);
}
