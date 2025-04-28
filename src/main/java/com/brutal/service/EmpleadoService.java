package com.brutal.service;

import com.brutal.generics.GenericService;
import com.brutal.model.mesero.Empleado;
import com.brutal.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService extends GenericService <Empleado, Long> {
    private final EmpleadoRepository empleadoRepository;
    public EmpleadoService(EmpleadoRepository empleadoRepository){
        super(empleadoRepository);
        this.empleadoRepository = empleadoRepository;
    }
}
