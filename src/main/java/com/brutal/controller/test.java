package com.brutal.controller;

import com.brutal.dto.DetallePedidoResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class test {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public test(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/ws/test")
    public void testWs() {
        DetallePedidoResponse fake = new DetallePedidoResponse();
        fake.setId(999L);
        fake.setNombre("Test Plato");
        fake.setCantidad(1);
        fake.setImg("https://via.placeholder.com/100");
        fake.setEstado("Pendiente");
        fake.setTipo("Cocina");

        simpMessagingTemplate.convertAndSend("/topic/hot", fake);
    }
}
