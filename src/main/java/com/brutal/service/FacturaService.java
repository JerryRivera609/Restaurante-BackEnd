package com.brutal.service;

import com.brutal.generics.GenericService;
import com.brutal.model.facturas.Factura;
import com.brutal.repository.FacturaRepository;
import org.springframework.stereotype.Service;

@Service
public class FacturaService extends GenericService <Factura, Long> {
    private final FacturaRepository facturaRepository;
    public FacturaService (FacturaRepository facturaRepository){
        super(facturaRepository);
        this.facturaRepository = facturaRepository;
    }
}
