package com.nahuel.proyectov1.api_rest_v1.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nahuel.proyectov1.api_rest_v1.models.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos,Long>{



}
