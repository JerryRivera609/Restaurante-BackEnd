package com.brutal.controller;

import com.brutal.dto.ProductoRequest;
import com.brutal.generics.GenericController;
import com.brutal.model.pedidos.Pedidos;
import com.brutal.model.productos.Productos;
import com.brutal.service.ProductosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductosController extends GenericController <Pedidos, Long> {
    private final ProductosService productosService;
    public ProductosController (ProductosService productosService){
        super(productosService);
        this.productosService = productosService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequest dto){
        try {
            Productos actualiza = productosService.ActualizarProducto(id, dto);
            return ResponseEntity.ok(actualiza);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
