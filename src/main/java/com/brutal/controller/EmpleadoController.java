package com.brutal.controller;

import com.brutal.dto.EmpleadosRequest;
import com.brutal.generics.GenericController;
import com.brutal.model.empleado.Empleado;
import com.brutal.model.empleado.Rol;
import com.brutal.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController extends GenericController <Empleado, Long> {
    private final EmpleadoService empleadoService;
    public EmpleadoController(EmpleadoService empleadoService){
        super(empleadoService);
        this.empleadoService = empleadoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearEmpleado(@RequestBody EmpleadosRequest dto){
        Empleado empleado = new Empleado();
        empleado.setNombre(dto.getNombre());
        empleado.setEmail(dto.getEmail());
        empleado.setContrasenia(dto.getContrasenia());
        Rol rol = Rol.valueOf(dto.getRol().toUpperCase());
        empleado.setRol(rol);
        empleadoService.saveEmpleado(empleado);
        return  ResponseEntity.status(HttpStatus.CREATED).body(empleado);
    }

}
