package com.brutal.service;

import com.brutal.generics.GenericService;
import com.brutal.model.mesas.Mesas;
import com.brutal.repository.MesasRepository;
import org.springframework.stereotype.Service;

@Service
public class MesasService extends GenericService <Mesas, Long> {
    private final MesasRepository mesasRepository;
    public MesasService (MesasRepository mesasRepository){
        super(mesasRepository);
        this.mesasRepository = mesasRepository;
    }
}
