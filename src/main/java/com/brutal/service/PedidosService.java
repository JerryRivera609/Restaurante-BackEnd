package com.brutal.service;

import com.brutal.dto.PedidoRequest;
import com.brutal.generics.GenericService;
import com.brutal.model.empleado.Empleado;
import com.brutal.model.mesas.Mesas;
import com.brutal.model.pedidos.Estado;
import com.brutal.model.pedidos.Pedidos;
import com.brutal.repository.EmpleadoRepository;
import com.brutal.repository.MesasRepository;
import com.brutal.repository.PedidosRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidosService extends GenericService <Pedidos, Long> {
    private final PedidosRepository pedidosRepository;
    private final EmpleadoRepository empleadoRepository;
    private final MesasRepository mesasRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public PedidosService (PedidosRepository pedidosRepository, EmpleadoRepository empleadoRepository, MesasRepository mesasRepository, SimpMessagingTemplate simpMessagingTemplate){
        super(pedidosRepository);
        this.pedidosRepository = pedidosRepository;
        this.empleadoRepository = empleadoRepository;
        this.mesasRepository = mesasRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public Pedidos crearPedido(PedidoRequest dto) {
        Empleado mesero = empleadoRepository.findById(dto.getIdEmpleado())
                .orElseThrow(() -> new EntityNotFoundException("Mesero no existe"));

        Mesas mesa = mesasRepository.findById(dto.getIdMesa())
                .orElseThrow(() -> new EntityNotFoundException("Mesa no existe"));


        //Actualizar el Estado de la mesa
        mesa.setEstado(com.brutal.model.mesas.Estado.Ocupado);
        mesasRepository.save(mesa);
        //Emitir el nuevo estado
        simpMessagingTemplate.convertAndSend("/topic/mesas", mesa);

        Pedidos pedido = new Pedidos();
        pedido.setEmpleado(mesero);
        pedido.setMesa(mesa);
        pedido.setEstado(Estado.Pendiente); // o el que uses por defecto

        return pedidosRepository.save(pedido);
    }

}
