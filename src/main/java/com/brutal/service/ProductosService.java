package com.brutal.service;

import com.brutal.generics.GenericService;
import com.brutal.model.pedidos.Pedidos;
import com.brutal.repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductosService extends GenericService <Pedidos, Long> {
    private final ProductoRepository productoRepository;
    public ProductosService (ProductoRepository productoRepository){
        super(productoRepository);
        this.productoRepository = productoRepository;
    }
}
