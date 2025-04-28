package com.brutal.controller;

import com.brutal.generics.GenericController;
import com.brutal.model.pedidos.Pedidos;
import com.brutal.service.ProductosService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductosController extends GenericController <Pedidos, Long> {
    private final ProductosService productosService;
    public ProductosController (ProductosService productosService){
        super(productosService);
        this.productosService = productosService;
    }
}
