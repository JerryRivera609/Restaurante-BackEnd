package com.brutal.service;

import com.brutal.dto.ActualizarFacturaRequest;
import com.brutal.dto.FacturaRequest;
import com.brutal.dto.FacturaResponse;
import com.brutal.generics.GenericService;
import com.brutal.model.detallePedido.DetallePedido;
import com.brutal.model.facturas.EstadoFactura;
import com.brutal.model.facturas.Factura;
import com.brutal.model.facturas.MedioDePago;
import com.brutal.model.pedidos.Pedidos;
import com.brutal.repository.DetallePedidoRepository;
import com.brutal.repository.FacturaRepository;
import com.brutal.repository.PedidosRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class FacturaService extends GenericService <Factura, Long> {
    private final FacturaRepository facturaRepository;
    private final PedidosRepository pedidosRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    public FacturaService (FacturaRepository facturaRepository, PedidosRepository pedidosRepository, DetallePedidoRepository detallePedidoRepository){
        super(facturaRepository);
        this.facturaRepository = facturaRepository;
        this.pedidosRepository = pedidosRepository;
        this.detallePedidoRepository = detallePedidoRepository;
    }

    public Factura crearFactura(FacturaRequest dto){

        Pedidos pedido = pedidosRepository.findById(dto.getIdPedido())
                .orElseThrow(()-> new RuntimeException("No se encontró el pedido"));

        Factura factura = new Factura();
        factura.setFechaHora(dto.getFecha());
        factura.setTotal(dto.getPrecioTotal());
        factura.setPedido(pedido);
        EstadoFactura estado = EstadoFactura.valueOf(dto.getEstado().toUpperCase());
        factura.setEstado(estado);
        MedioDePago medioDePago = MedioDePago.valueOf(dto.getMedioDePago().toUpperCase());
        factura.setMedioDePago(medioDePago);
        return facturaRepository.save(factura);
    }

    public List<FacturaResponse> obtenerTodasLasFacturas() {
        List<Factura> facturas = facturaRepository.findAll();

        return facturas.stream().map(factura -> {
            FacturaResponse dto = new FacturaResponse();

            dto.setId(factura.getId());
            dto.setFecha(factura.getFechaHora());
            dto.setEstado(factura.getEstado().name());
            dto.setMedioDePago(
                    factura.getMedioDePago() != null ? factura.getMedioDePago().name() : "SIN_MEDIO"
            );
            dto.setPrecioTotal(factura.getTotal());

            Pedidos pedidos = factura.getPedido();
            FacturaResponse.PedidoInfo pedidoInfo = new FacturaResponse.PedidoInfo();
            pedidoInfo.setIdPedido(pedidos.getId());
            pedidoInfo.setNumeroMesa(pedidos.getMesa().getNumero());

            List<DetallePedido> detalles = detallePedidoRepository.findByPedido(pedidos);
            List<FacturaResponse.DetallePedidoInfo> detallesDTO = detalles.stream().map(detalle -> {
                FacturaResponse.DetallePedidoInfo detalleDTO = new FacturaResponse.DetallePedidoInfo();
                detalleDTO.setNombreCompleto(detalle.getProducto().getNombre());
                detalleDTO.setPrecio(detalle.getProducto().getPrecio());
                detalleDTO.setCantidad(detalle.getCantidad());
                return detalleDTO;
            }).collect(Collectors.toList());

            pedidoInfo.setDetalles(detallesDTO);
            dto.setPedidoInfo(pedidoInfo);

            return dto;
        }).collect(Collectors.toList());
    }

    public void actualizarFactura (Long id, ActualizarFacturaRequest request){
        Factura factura = facturaRepository.findById(id).
                orElseThrow(()-> new RuntimeException("No se encontró el id de la factura que se desea aactualizar"));
        if (request.getEstado() != null){
            factura.setEstado(EstadoFactura.valueOf(request.getEstado()));
        }
        if (request.getMedioDePago() != null){
            factura.setMedioDePago(MedioDePago.valueOf(request.getMedioDePago()));
        }

        facturaRepository.save(factura);
    }


}
