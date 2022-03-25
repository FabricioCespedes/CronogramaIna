/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "PROFESORES")
public class Profesor implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="ID_PROFESOR")
    private Long idProfesor;
    
    @Column(name = "NOMBRE_PROFESOR")
    @NotEmpty(message="El Nombre del profesor es obligatorio")
    private String nombre;
    
    @Column(name = "APELLIDO1_PROFESOR")
    @NotEmpty(message="El primer apellido del profesor es Obligatorio")
    private String apellido1;
    
    @Column(name = "APELLIDO2_PROFESOR")
    private String apellido2;
    
    //Relación de muchos profeosres a un centro 
    @JoinColumn(name = "ID_CENTRO", nullable = false)
    @ManyToOne(optional = false)
    private Centro centro;
    
//    //Relación de un profesor a muchos dias ausentes
    @OneToMany(mappedBy = "profesor")
    private List<DiaAusente> diasAusentes;
 
    //Relacion de un profesor a muchas asignaciones de usuario
    @OneToMany(mappedBy = "profesor")
    private List<UsuariosProfesores> usuarioProfesor;
//    
//    //Relacion de un profesor a muchas asignaciones de programa
    @OneToMany(mappedBy = "profesor")
    private List<AsignacionProfesor> asignacionProfesor;    
        
    
    
}
