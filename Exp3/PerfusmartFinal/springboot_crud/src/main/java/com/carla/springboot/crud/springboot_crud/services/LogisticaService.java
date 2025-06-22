package com.carla.springboot.crud.springboot_crud.services;
import java.util.List;
import java.util.Optional;

import com.carla.springboot.crud.springboot_crud.entities.Logistica;

public interface LogisticaService {

    List<Logistica> findByAll();

    Optional<Logistica> findById(Long idEnvio);//optional validador en caso de que no se encuentre un dato

    Logistica save(Logistica laLogistica);

    Optional<Logistica> delete(Logistica laLogistica);

}