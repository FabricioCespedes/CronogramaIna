/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIOS_PROFESORES")
public class UsuariosProfesores implements Serializable {
    
    private static final long serialVersionUID=1L;
    
    //Relación de muchos profesores a una asignación
    @Id
    @JoinColumn(name = "ID_PROFESOR", nullable = false)
    @ManyToOne(optional = false)
    private Profesor profesor;
    
    //Relación de muchos administradores a una asignación
    @Id
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    
    @Id
    @Column(name = "ANIO")
    private int anio;
    
    
}
