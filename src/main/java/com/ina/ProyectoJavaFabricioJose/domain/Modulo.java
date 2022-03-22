/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "MODULOS")
public class Modulo implements Serializable {
    
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MODULO")
    private int idModulo;
    
    @Column(name = "CODIGO")
    @NotEmpty(message="El código del módulo es obligatorio")
    private String codigo;
    
    @Column(name = "NOMBRE_MODULO")
    @NotEmpty(message="El nombre del módulo es obligatorio")
    private String nombreModulo;
    
    //Relación de un módulo requiere uno
    @JoinColumn(name = "ID_MODULO", nullable = false)
    @OneToOne(optional = false)
    private Modulo modulo;
    
    @Column(name = "HORAS_TOTALES")
    @NotEmpty(message="Las horas totales del módulo son obligatorias")
    private int horasTotales;
    
    //Relacion de un modulo a muchas asignaciones de programa
    @OneToMany(mappedBy = "modulo")
    private List<AsignacionProfesor> asignacionProfesor;
    
    //Relacion de un modulo a muchos cronogramas
    @OneToMany(mappedBy = "modulo")
    private List<Cronograma> cronograma;    

    
}
