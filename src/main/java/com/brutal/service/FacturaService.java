package com.brutal.service;

import com.brutal.dto.FacturaRequest;
import com.brutal.generics.GenericService;
import com.brutal.model.facturas.EstadoFactura;
import com.brutal.model.facturas.Factura;
import com.brutal.model.facturas.MedioDePago;
import com.brutal.model.pedidos.Pedidos;
import com.brutal.repository.FacturaRepository;
import com.brutal.repository.PedidosRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class FacturaService extends GenericService <Factura, Long> {
    private final FacturaRepository facturaRepository;
    private final PedidosRepository pedidosRepository;
    public FacturaService (FacturaRepository facturaRepository, PedidosRepository pedidosRepository){
        super(facturaRepository);
        this.facturaRepository = facturaRepository;
        this.pedidosRepository = pedidosRepository;
    }

    public Factura crearFactura(FacturaRequest dto){

        Pedidos pedido = pedidosRepository.findById(dto.getIdPedido())
                .orElseThrow(()-> new RuntimeException("No se encontró el pedido"));

        Factura factura = new Factura();
        factura.setFechaHora(dto.getFecha());
        factura.setTotal(dto.getPrecioTotal());
        factura.setPedido(pedido);
        EstadoFactura estado = EstadoFactura.valueOf(dto.getEstado().toUpperCase());
        factura.setEstadoFactura(estado);
        MedioDePago medioDePago = MedioDePago.valueOf(dto.getMedioDePago().toUpperCase());
        factura.setMedioDePago(medioDePago);
        return facturaRepository.save(factura);
    }
}
