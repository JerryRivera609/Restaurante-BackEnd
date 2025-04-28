package com.brutal.service;

import com.brutal.generics.GenericService;
import com.brutal.model.detallePedido.DetallePedido;
import com.brutal.repository.DetallePedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoService extends GenericService <DetallePedido, Long> {
    private final DetallePedidoRepository detallePedidoRepository;
    public DetallePedidoService (DetallePedidoRepository detallePedidoRepository){
        super(detallePedidoRepository);
        this.detallePedidoRepository = detallePedidoRepository;
    }

}
