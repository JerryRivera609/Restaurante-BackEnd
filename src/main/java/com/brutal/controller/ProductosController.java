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

    @PostMapping("/guardar")
    public ResponseEntity<Productos> crearProducto(@RequestBody ProductoRequest dto){
        Productos productos = productosService.crearProductos(dto);
        return ResponseEntity.ok(productos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequest dto){
            Productos actualizado = productosService.ActualizarProducto(id, dto);
            return ResponseEntity.ok(actualizado);

    }
}
