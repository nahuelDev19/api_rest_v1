package com.nahuel.proyectov1.api_rest_v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nahuel.proyectov1.api_rest_v1.Repositorys.ProductosRepository;
import com.nahuel.proyectov1.api_rest_v1.models.Productos;

@Service
public class ProductosServiceImp implements ProductoService{

    @Autowired
    private ProductosRepository productosRepository;

    /*
     * obtiene todos los productos
     * @return una lista de productos 
     */
    @Transactional(readOnly = true)
    @Override
    public List<Productos> findAll() {
        return productosRepository.findAll();
    }

    /*
     *busca el producto por su ID
     * 
     * @param id el ID del producto a buscar
     * @return returna el producto si se encuentra
    */

    @Transactional(readOnly = true)
    @Override
    public Optional<Productos> findById(Long id) {
        return productosRepository.findById(id);
    }

    /*
     * guarda nuevos productos
     * 
     * @param producto producto a guardar
     * @return productos guardados
    */

    @Transactional
    @Override
    public Productos save(Productos producto) {
        return productosRepository.save(producto);
    }


    /*
     * actualiza productos existentes
     * 
     * @param id utiiza el ID del producto a actualizar
     * @param productos los nuevos datos del producto
     * @return retorna el producto actualizado
     * 
    */

    @Transactional
    @Override
    public Optional<Productos> update(Long id, Productos productos) {
        Optional<Productos> productoUpdate= productosRepository.findById(id);
        if (productoUpdate.isPresent()) {
        Productos produ= productoUpdate.orElseThrow();
        produ.setNombre(productos.getNombre());
        produ.setPrecio(productos.getPrecio());
        produ.setDescripcion(productos.getDescripcion());
        return Optional.of(productosRepository.save(produ));
        }
        return productoUpdate;
    }

    /*
     * 
     * elimina el producto por su id
     * 
     * @param id utiliza el ID del producto para eliminar
     * @return true si se elimino con exito, false si el producto no existe
    */

    @Transactional
    @Override
    public Boolean delete(Long id) {
        Optional<Productos> productosDelete= productosRepository.findById(id);
        if (productosDelete.isPresent()) {
            productosRepository.delete(productosDelete.get());
            return true;
        }
        return false;
    }



}
