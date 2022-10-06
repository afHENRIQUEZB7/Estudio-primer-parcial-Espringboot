package com.prueba_Parcial1.Estudio_primer_Parcial.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name="usuarios")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nombre;
    @Column(length = 500, nullable = false)
    private String descripcion;
    @Column( nullable = false)
    private Double valor;
    @Column(length = 200)
    private String empresa;


}
