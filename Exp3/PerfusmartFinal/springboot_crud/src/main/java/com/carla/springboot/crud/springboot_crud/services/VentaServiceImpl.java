package com.carla.springboot.crud.springboot_crud.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carla.springboot.crud.springboot_crud.entities.Venta;
import com.carla.springboot.crud.springboot_crud.repository.venta.VentaRepository;


@Service
public class VentaServiceImpl implements VentaService{

    @Autowired
    private VentaRepository repository;

    @Override
    @Transactional(readOnly= true)//es read only porque es solo para verlo c:
    public List<Venta> findByAll() {
        return (List<Venta>) repository.findAll();
    }


    @Override
    @Transactional(readOnly= true)
    public Optional<Venta > findById(Long id) {  
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Venta save(Venta unVenta ) {
        return repository.save(unVenta );
    }
    

    @Override
    @Transactional
    public Optional<Venta> delete(Venta unVenta ) {
        Optional <Venta> ventaOptional= repository.findById(unVenta .getidVenta());
        ventaOptional.ifPresent(ventaDb->{
            repository.delete(unVenta);
        });
        return ventaOptional;
    }



}