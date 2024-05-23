package com.nahuel.proyectov1.api_rest_v1.service;

import java.util.List;
import java.util.Optional;

import com.nahuel.proyectov1.api_rest_v1.models.Productos;

public interface ProductoService {

    List<Productos> findAll();
    Optional<Productos> findById(Long id);
    Productos save(Productos producto);
    Optional<Productos> update(Long id, Productos productos);
    Boolean delete(Long id);

}
