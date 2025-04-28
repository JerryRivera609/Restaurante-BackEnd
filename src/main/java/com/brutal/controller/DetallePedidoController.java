package com.brutal.controller;


import com.brutal.generics.GenericController;
import com.brutal.model.detallePedido.DetallePedido;
import com.brutal.service.DetallePedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detallepedido")
public class DetallePedidoController extends GenericController <DetallePedido, Long> {
    private final DetallePedidoService detallePedidoService;
    public DetallePedidoController (DetallePedidoService detallePedidoService){
        super(detallePedidoService);
        this.detallePedidoService = detallePedidoService;
    }
}
