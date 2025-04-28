package com.brutal.service;

import com.brutal.generics.GenericService;
import com.brutal.model.pedidos.Pedidos;
import com.brutal.repository.PedidosRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidosService extends GenericService <Pedidos, Long> {
    private final PedidosRepository pedidosRepository;
    public PedidosService (PedidosRepository pedidosRepository){
        super(pedidosRepository);
        this.pedidosRepository = pedidosRepository;
    }
}
