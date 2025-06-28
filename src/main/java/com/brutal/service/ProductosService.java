package com.brutal.service;

import com.brutal.dto.ProductoRequest;
import com.brutal.generics.GenericService;
import com.brutal.model.pedidos.Pedidos;
import com.brutal.model.productos.Productos;
import com.brutal.model.productos.Tipo;
import com.brutal.repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductosService extends GenericService <Pedidos, Long> {
    private final ProductoRepository productoRepository;
    public ProductosService (ProductoRepository productoRepository){
        super(productoRepository);
        this.productoRepository = productoRepository;
    }

    public Productos ActualizarProducto(Long id, ProductoRequest dto){
        Productos productos = productoRepository.findById(id).
                orElseThrow(()-> new RuntimeException("No se Encontró el producto"));
        productos.setNombre(dto.getNombre());
        Tipo tipo = Tipo.valueOf(dto.getTipo().toUpperCase());
        productos.setTipo(tipo);
        productos.setPrecio(dto.getPrecio());
        productos.setResumen(dto.getResumen());
        productos.setImg(dto.getImg());

        return productoRepository.save(productos);
    }
}
