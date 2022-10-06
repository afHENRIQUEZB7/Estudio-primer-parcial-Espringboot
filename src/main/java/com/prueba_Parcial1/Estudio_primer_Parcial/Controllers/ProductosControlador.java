package com.prueba_Parcial1.Estudio_primer_Parcial.Controllers;


import com.prueba_Parcial1.Estudio_primer_Parcial.Models.Usuario;
import com.prueba_Parcial1.Estudio_primer_Parcial.Repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class ProductosControlador {

    @Autowired
    private ProductosRepository productosRepository;

    @GetMapping(value = "/producto/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
        Optional<Usuario> usuario= productosRepository.findById(id);
        if (usuario.isPresent()){
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/producto")
    public ResponseEntity crearProductos(@RequestBody Usuario usuario){
        try{
            productosRepository.save(usuario);
            return new ResponseEntity(usuario, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/producto/nombre/{nombre}")
    public ResponseEntity ListarPorNombre(@PathVariable String nombre){
        List<Usuario> productos= productosRepository.findAllByNombre(nombre);
        if (productos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(productos,HttpStatus.OK);
    }

    @GetMapping("/producto/valor/{valor}")
    public  ResponseEntity ListarPorPrecio(@PathVariable Double valor){
        List<Usuario> valorProducto= productosRepository.findAllByValor(valor);
        if (valorProducto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(valorProducto, HttpStatus.OK);
    }

    @GetMapping("/producto/nombre/descripcion/{nombre}/{descripcion}")
    public ResponseEntity ListarPorNombreYDescricion(@PathVariable String nombre, @PathVariable String descripcion){
        List<Usuario> NombreDescripcion=productosRepository.findAllByNombreAndDescripcion(nombre,descripcion);
        if (NombreDescripcion.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  new ResponseEntity(NombreDescripcion,HttpStatus.OK);
    }

    @GetMapping("/productos")
    public ResponseEntity ListarTodosLosProductos(){
        List<Usuario> productos= productosRepository.findAll();
        if (productos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  new ResponseEntity(productos,HttpStatus.OK);
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity editarProductos (@PathVariable Long id, @RequestBody Usuario producto){
        Optional<Usuario> productoBD= productosRepository.findById(id);
        if (productoBD.isPresent()){
            try{
                productoBD.get().setNombre(producto.getNombre());
                productoBD.get().setDescripcion(producto.getDescripcion());
                productoBD.get().setValor(producto.getValor());
                productoBD.get().setEmpresa(producto.getEmpresa());
                productosRepository.save(productoBD.get());
                return new ResponseEntity(productoBD, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity eliminarProducto(@PathVariable Long id){
        Optional<Usuario> productoBD= productosRepository.findById(id);
        if (productoBD.isPresent()){
            productosRepository.delete(productoBD.get());
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }

}
