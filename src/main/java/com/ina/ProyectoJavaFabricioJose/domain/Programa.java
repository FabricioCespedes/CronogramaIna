/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "PROGRAMAS")
public class Programa implements Serializable {
    
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROGRAMA")
    private int idPrograma;
    
    
    @Column(name = "CODIGO", unique = true)
    @NotEmpty(message="El código del programa es obligatorio")
    private String codigo;
    
    @Column(name = "NOMBRE_PROGRAMA")
    @NotEmpty(message="El nombre del programa es obligatorio")
    private String nombrePrograma;
    
    
    @Column(name = "HORAS_DIA")
    @NotEmpty(message="Las horas por día del programa son obligatorias")
    private double horasDia;
    
    @Column(name = "HORA_INICIO")
    @NotEmpty(message="Las horas por día del programa son obligatorias")
    private Time horaInicio;
    
    @Column(name = "HORA_FIN")
    @NotEmpty(message="Las horas por día del programa son obligatorias")
    private Time horaFin;
    
    @Column(name = "ESTADO")
    @NotEmpty(message="El estado del programa es obligatorio")
    private String estado;
    
    @Column(name = "ANIO")
    @NotEmpty(message="El año del programa es obligatorio")
    private int anio;
    
    //Relación de muchos programas a un centro
    @JoinColumn(name = "ID_CENTRO", nullable = false)
    @ManyToOne(optional = false)
    private Centro centro;
    
    @Column(name = "GRUPO")
    @NotEmpty(message="El grupo que recibe el programa es obligatorio")
    private int grupo;

    //Relacion de un programa a muchas asignaciones de programa
    @OneToMany(mappedBy = "programa")
    private List<AsignacionProfesor> asignacionProfesor;
    
    //Relacion de un programa a muchos cronogramas
    @OneToMany(mappedBy = "programa")
    private List<Cronograma> cronograma;
    
     //Relacion de un modulo a muchos cronogramas
    @OneToMany(mappedBy = "programa")
    private List<DiasProgramas> diasProgramas;
}
