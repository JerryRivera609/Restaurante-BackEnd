package com.brutal.controller;

import com.brutal.dto.PedidoRequest;
import com.brutal.generics.GenericController;
import com.brutal.model.pedidos.Pedidos;
import com.brutal.service.PedidosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/crear")
    public ResponseEntity<Pedidos> creaPedido(@RequestBody PedidoRequest pedidoRequest){
        Pedidos nuevo = pedidosService.crearPedido(pedidoRequest);
        return ResponseEntity.ok(nuevo);
    }
}
