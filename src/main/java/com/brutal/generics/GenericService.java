package com.brutal.generics;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenericService <T extends BaseEntity, ID>{
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

    public T actualizar(T t) {
        Optional<T> buscar = genericRepository.findById(t.getId());
        if (buscar.isPresent()) {
            return (T) genericRepository.save(t);
        }
        return null;
    }
    public void eliminarPorId(ID id){
        genericRepository.deleteById(id);
    }


}
