package com.carla.springboot.crud.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import com.carla.springboot.crud.springboot_crud.entities.Logistica;
import com.carla.springboot.crud.springboot_crud.repository.logistica.LogisticaRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticaServiceImpl implements LogisticaService{

    @Autowired
    private LogisticaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Logistica> findByAll() {
        return (List<Logistica>) repository.findAll();
    }

    @Override
    @Transactional(readOnly= true)
    public Optional<Logistica> findById(Long idEnvio) {  
        return repository.findById(idEnvio);
    }

    @Override
    @Transactional
    public Logistica save(Logistica laLogistica) {
        return repository.save(laLogistica);
    }

    @Override
    @Transactional
    public Optional<Logistica> delete(Logistica laLogistica) {
        Optional <Logistica> logisticaOptional= repository.findById(laLogistica.getIdEnvio());
        logisticaOptional.ifPresent(logisticaDb->{
            repository.delete(laLogistica);
        });
        return logisticaOptional;
    }
    
}