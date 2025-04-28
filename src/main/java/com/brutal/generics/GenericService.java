package com.brutal.generics;

import java.util.List;
import java.util.Optional;

public class GenericService <T extends BaseEntity, Long>{
    private final GenericRepository genericRepository;

    public GenericService(GenericRepository genericRepository){
        this.genericRepository = genericRepository;
    }

    public List<T> listar(){
        return genericRepository.findAll();
    }

    public void guardar(T t){
        genericRepository.save(t);
    }

    public void eliminar(T t){
        genericRepository.delete(t);
    }

    public void actualizar(T t){
        Optional buscar =genericRepository.findById(t. getId());
        if(buscar.isPresent()){
            genericRepository.save(t);
        }
    }
    public void eliminarPorId(Long id){
        genericRepository.deleteById(id);
    }

}
