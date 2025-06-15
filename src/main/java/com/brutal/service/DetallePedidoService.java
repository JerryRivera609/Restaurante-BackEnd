package com.brutal.service;

import com.brutal.dto.DetallePedidoResponse;
import com.brutal.dto.ListaDetallePedidoRequest;
import com.brutal.generics.GenericService;
import com.brutal.model.detallePedido.DetallePedido;
import com.brutal.model.detallePedido.EstadoPedido;
import com.brutal.model.pedidos.Pedidos;
import com.brutal.model.productos.Productos;
import com.brutal.repository.DetallePedidoRepository;
import com.brutal.repository.PedidosRepository;
import com.brutal.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetallePedidoService extends GenericService<DetallePedido, Long> {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidosRepository pedidosRepository;
    private final ProductoRepository productoRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public DetallePedidoService(
            DetallePedidoRepository detallePedidoRepository,
            PedidosRepository pedidosRepository,
            ProductoRepository productoRepository,
            SimpMessagingTemplate simpMessagingTemplate
    ) {
        super(detallePedidoRepository);
        this.detallePedidoRepository = detallePedidoRepository;
        this.pedidosRepository = pedidosRepository;
        this.productoRepository = productoRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public DetallePedidoResponse mapearADetallePedidoResponse(DetallePedido detalle) {
        DetallePedidoResponse response = new DetallePedidoResponse();
        response.setId(detalle.getId());
        response.setNombre(detalle.getProducto().getNombre());
        response.setImg(detalle.getProducto().getImg());
        response.setTipo(detalle.getProducto().getTipo().toString());
        response.setCantidad(detalle.getCantidad());
        response.setEstado(detalle.getEstado().toString());
        return response;
    }

    public List<DetallePedidoResponse> obtenerDetallesPorEstado(EstadoPedido estado) {
        List<DetallePedido> detalles = detallePedidoRepository.findByEstado(estado);
        return detalles.stream()
                .map(this::mapearADetallePedidoResponse)
                .toList();
    }

    public DetallePedido actualizarEstado(Long id, String estado){
        DetallePedido detallePedido = detallePedidoRepository
                .findById(id).orElseThrow(() -> new RuntimeException("No se encontró el id" + id));
        detallePedido.setEstado(EstadoPedido.valueOf(estado));
        return detallePedidoRepository.save(detallePedido);
    }

    public void guardarDetalles(ListaDetallePedidoRequest request) {
        Pedidos pedido = pedidosRepository.findById(request.getIdPedido())
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado"));

        List<DetallePedido> detalles = new ArrayList<>();

        for (ListaDetallePedidoRequest.DetalleItem item : request.getItems()) {
            Productos producto = productoRepository.findById(item.getIdProducto())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecio(item.getPrecio());
            detalle.setEstado(EstadoPedido.Pendiente); // se crean como pendientes

            detalles.add(detalle);
        }

        // guardar todos los detalles
        List<DetallePedido> guardados = detallePedidoRepository.saveAll(detalles);

        // Se emite  en WebSocket por tipo
        detallePedidoRepository.saveAll(detalles);

        //for (DetallePedido detalle : guardados) {
        //    DetallePedidoResponse response = mapearADetallePedidoResponse(detalle);
        //    String tipo = detalle.getProducto().getTipo().toString().toLowerCase(); // HOT → "hot"
        //    simpMessagingTemplate.convertAndSend("/topic/" + tipo, response);
        //}

        // envviar por websdocket cada detalle guardado
        for (DetallePedido detalle : detalles) {
            DetallePedidoResponse response = mapearADetallePedidoResponse(detalle);
            if ("Cocina".equals(response.getTipo())) {
                simpMessagingTemplate.convertAndSend("/topic/hot", response);
            }
        }

    }
}
