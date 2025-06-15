package com.brutal.controller;

import com.brutal.dto.FacturaRequest;
import com.brutal.generics.GenericController;
import com.brutal.model.facturas.Factura;
import com.brutal.service.FacturaService;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factura")
public class FacturaController extends GenericController <Factura, Long> {
    private final FacturaService facturaService;
    public FacturaController (FacturaService facturaService){
        super(facturaService);
        this.facturaService = facturaService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Factura> guardarFactura(@RequestBody FacturaRequest facturaRequest){
        Factura factura = facturaService.crearFactura(facturaRequest);
        return ResponseEntity.ok(factura);
    }
}
