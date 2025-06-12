package com.brutal.controller;


import com.brutal.dto.DetallePedidoResponse;
import com.brutal.dto.ListaDetallePedidoRequest;
import com.brutal.generics.GenericController;
import com.brutal.model.detallePedido.DetallePedido;
import com.brutal.model.detallePedido.EstadoPedido;
import com.brutal.model.mesas.Estado;
import com.brutal.repository.DetallePedidoRepository;
import com.brutal.service.DetallePedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/detallepedido")
public class DetallePedidoController extends GenericController <DetallePedido, Long> {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController (DetallePedidoService detallePedidoService){
        super(detallePedidoService);
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<DetallePedidoResponse>> obtenerPorEstado(@PathVariable EstadoPedido estado) {
        return ResponseEntity.ok(detallePedidoService.obtenerDetallesPorEstado(estado));
    }

    @PostMapping("/guardar")
    public ResponseEntity<Void> guardarDetalles(
            @RequestBody ListaDetallePedidoRequest request) {
        detallePedidoService.guardarDetalles(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body){
        String nuevoEstado = body.get("estado");
        DetallePedido actualizado = detallePedidoService.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(actualizado);
    }
}
