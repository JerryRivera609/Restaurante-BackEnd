package com.brutal.controller;

import com.brutal.dto.ActualizarFacturaRequest;
import com.brutal.dto.FacturaRequest;
import com.brutal.dto.FacturaResponse;
import com.brutal.generics.GenericController;
import com.brutal.model.facturas.Factura;
import com.brutal.service.FacturaService;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factura")
public class FacturaController extends GenericController <Factura, Long> {
    private final FacturaService facturaService;
    @Autowired
    public FacturaController (FacturaService facturaService){
        super(facturaService);
        this.facturaService = facturaService;
    }

    @GetMapping("/detalleFactura")
    public ResponseEntity<List<FacturaResponse>> obtenerFacturas(){
        return ResponseEntity.ok(facturaService.obtenerTodasLasFacturas());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Factura> guardarFactura(@RequestBody FacturaRequest facturaRequest){
        Factura factura = facturaService.crearFactura(facturaRequest);
        return ResponseEntity.ok(factura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarFactura(@PathVariable Long id, @RequestBody ActualizarFacturaRequest request){
        System.out.println("Estado recibido: " + request.getEstado());
        System.out.println("Medio de pago recibido: " + request.getMedioDePago());
        facturaService.actualizarFactura(id, request);
        return ResponseEntity.ok("Factura Actualizada");
    }
}
