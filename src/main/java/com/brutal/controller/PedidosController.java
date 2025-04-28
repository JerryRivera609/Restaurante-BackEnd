package com.brutal.controller;

import com.brutal.generics.GenericController;
import com.brutal.service.PedidosService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController extends GenericController {
    private final PedidosService pedidosService;
    public PedidosController (PedidosService pedidosService){
        super(pedidosService);
        this.pedidosService = pedidosService;
    }
}
