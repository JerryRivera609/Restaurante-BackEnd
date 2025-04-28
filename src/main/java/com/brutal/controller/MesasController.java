package com.brutal.controller;

import com.brutal.generics.GenericController;
import com.brutal.model.mesas.Mesas;
import com.brutal.service.MesasService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mesas")
public class MesasController extends GenericController <Mesas, Long> {
    private final MesasService mesasService;
    public MesasController (MesasService mesasService){
        super(mesasService);
        this.mesasService = mesasService;
    }
}

