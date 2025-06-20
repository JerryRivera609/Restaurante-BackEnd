package com.brutal.repository;

import com.brutal.generics.GenericRepository;
import com.brutal.model.facturas.EstadoFactura;
import com.brutal.model.facturas.Factura;

import java.util.List;

public interface FacturaRepository extends GenericRepository <Factura, Long> {
}
