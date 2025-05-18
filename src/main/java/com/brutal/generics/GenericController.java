package com.brutal.generics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public class GenericController <T extends BaseEntity, ID>{
    private final GenericService genericService;

    public GenericController(GenericService genericService){
        this.genericService = genericService;
    }

    @GetMapping
    public List<T> findAll(){
        return genericService.listar();
    }



    @PostMapping
    public T crear(@RequestBody T t){
        genericService.guardar(t);
        return t;
    }

    @DeleteMapping("/{id}")
    public Long eliminar(@PathVariable("id") Long id){
        genericService.eliminarPorId(id);
        return id;
    }

    @PutMapping("/update")
    public T actualizar(@PathVariable("id") Long id, @RequestBody T t){
        t.setId(id);
        genericService.actualizar(t);
        return t;
    }

}
