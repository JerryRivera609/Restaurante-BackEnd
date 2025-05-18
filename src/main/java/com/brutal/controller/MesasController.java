package com.brutal.controller;

import com.brutal.generics.GenericController;
import com.brutal.model.mesas.Mesas;
import com.brutal.service.MesasService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/mesas")
public class MesasController extends GenericController <Mesas, Long> {
    private final MesasService mesasService;
    private final SimpMessagingTemplate messagingTemplate;
    public MesasController (MesasService mesasService, SimpMessagingTemplate messagingTemplate){
        super(mesasService);
        this.mesasService = mesasService;
        this.messagingTemplate = messagingTemplate;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Mesas> getById(@PathVariable Long id) {
        Optional<Mesas> mesa = mesasService.buscarPorId(id);
        return mesa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesas> actualizarYEmitir(@PathVariable Long id, @RequestBody Mesas mesa) {
        mesa.setId(id);
        Mesas actualizada = mesasService.actualizar(mesa);
        messagingTemplate.convertAndSend("/topic/mesas", actualizada);
        return ResponseEntity.ok(actualizada);
    }
}

