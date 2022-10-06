package com.prueba_Parcial1.Estudio_primer_Parcial.Repository;




import com.prueba_Parcial1.Estudio_primer_Parcial.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductosRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAllByNombre(String nombre);

    List<Usuario> findAllByValor(Double valor);

    List<Usuario> findAllByNombreAndDescripcion(String nombre, String descripcion);



}
