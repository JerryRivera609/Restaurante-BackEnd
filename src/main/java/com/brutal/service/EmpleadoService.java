package com.brutal.service;

import com.brutal.dto.EmpleadosRequest;
import com.brutal.generics.GenericService;
import com.brutal.model.empleado.Empleado;
import com.brutal.model.empleado.Rol;
import com.brutal.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import javax.crypto.ExemptionMechanism;

@Service
public class EmpleadoService extends GenericService <Empleado, Long> {
    private final EmpleadoRepository empleadoRepository;
    public EmpleadoService(EmpleadoRepository empleadoRepository){
        super(empleadoRepository);
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado saveEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    public Empleado actualizarEmpleado(Long id, EmpleadosRequest dto){
        Empleado empleado = empleadoRepository.findById(id).
                orElseThrow(()-> new RuntimeException("No se encontro al empleado"));

        empleado.setNombre(dto.getNombre());
        empleado.setEmail(dto.getEmail());
        empleado.setContrasenia(dto.getContrasenia());
        Rol rol = Rol.valueOf(dto.getRol().toUpperCase());
        empleado.setRol(rol);

        return empleadoRepository.save(empleado);
    }

    public void desactivarEmpleado(Long id){
        Empleado empleado = empleadoRepository.findById(id).
                orElseThrow(()-> new RuntimeException("No se encontro el usuario"));
        empleado.setActivo(false);
        empleadoRepository.save(empleado);
    }
}
