package com.brutal.generics;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class GenericController <T extends BaseEntity, Long>{
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

    @PutMapping
    public T guardar(@RequestBody T t){
        genericService.actualizar(t);
        return t;
    }

}
