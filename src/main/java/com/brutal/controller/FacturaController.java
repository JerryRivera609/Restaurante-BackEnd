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

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/fecha")
    public ResponseEntity<?> obtenerFacturaPorFecha(String fecha){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaParseada = LocalDate.parse(fecha, formatter);

            List<Factura> facturas = facturaService.obtenerFacturaPorFecha(fechaParseada);
            return ResponseEntity.ok(facturas);

        } catch(DateTimeException e){
            return ResponseEntity.badRequest().body("Formato de fecha invalido, el formato es dd/MM/yyyy");
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Factura> guardarFactura(@RequestBody FacturaRequest facturaRequest){
        Factura factura = facturaService.crearFactura(facturaRequest);
        return ResponseEntity.ok(factura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarFactura(@PathVariable Long id, @RequestBody ActualizarFacturaRequest request){
        facturaService.actualizarFactura(id, request);
        return ResponseEntity.ok("Factura Actualizada");
    }
}
