package com.nahuel.proyectov1.api_rest_v1;

//import java.net.http.HttpResponse;
import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nahuel.proyectov1.api_rest_v1.models.Productos;
import com.nahuel.proyectov1.api_rest_v1.service.ProductoService;

@RestController
@RequestMapping("/app")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/findAll")
    public ResponseEntity<?> list(){
        List<Productos> listadOptional= productoService.findAll();
        if (listadOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listadOptional,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Productos productos){
        Optional<Productos> actualizable= productoService.update(id, productos);
        if (actualizable.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(actualizable.orElseThrow()));
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findByid(@PathVariable Long id){
        Optional<Productos> buscando= productoService.findById(id);

        if (buscando.isPresent()) {
            return ResponseEntity.ok(buscando.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Productos productos){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(productos));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Productos> eli= productoService.findById(id);
        if (eli.isPresent()) {
            productoService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }



}
