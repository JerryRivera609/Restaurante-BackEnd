package com.brutal.controller;

import com.brutal.generics.GenericController;
import com.brutal.model.facturas.Factura;
import com.brutal.service.FacturaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/factura")
public class FacturaController extends GenericController <Factura, Long> {
    private final FacturaService facturaService;
    public FacturaController (FacturaService facturaService){
        super(facturaService);
        this.facturaService = facturaService;
    }
}
