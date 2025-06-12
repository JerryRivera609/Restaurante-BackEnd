package com.brutal.controller;

import com.brutal.generics.GenericController;
import com.brutal.model.empleado.Empleado;
import com.brutal.service.EmpleadoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController extends GenericController <Empleado, Long> {
    private final EmpleadoService empleadoService;
    public EmpleadoController(EmpleadoService empleadoService){
        super(empleadoService);
        this.empleadoService = empleadoService;
    }

}
