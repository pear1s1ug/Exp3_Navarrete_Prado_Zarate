package com.carla.springboot.crud.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import com.carla.springboot.crud.springboot_crud.entities.Producto;
import com.carla.springboot.crud.springboot_crud.repository.ProductoRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {
    // Transactional= si la transaccion no se hace completamente, hace un rollback(ctrl+z), de lo contrario genera un commit(guarda o actualiza)
    //autowired= define automaticamente las notaciones
    @Autowired
    private ProductoRepository repository;

    @Override
    @Transactional(readOnly= true)//es read only porque es solo para verlo c:
    public List<Producto> findByAll() {
        return (List<Producto>) repository.findAll();
    }



    @Override
    @Transactional(readOnly= true)
    public Optional<Producto> findById(Long id) {  
        return repository.findById(id);
    }


    
    @Override
    @Transactional
    public Producto save(Producto unProducto) {
        return repository.save(unProducto);
    }
    

    @Override
    @Transactional
    public Optional<Producto> delete(Producto unProducto) {
        Optional <Producto> productoOptional= repository.findById(unProducto.getId());
        productoOptional.ifPresent(productoDb->{
            repository.delete(unProducto);
        });
        return productoOptional;
    }
}
