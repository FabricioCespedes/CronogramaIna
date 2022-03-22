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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "DISTRITOS")
public class Distrito implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DISTRITO")
    private Long idDistrito;
    
    @NotEmpty(message = "Por favor, ingrese el nombre del distrito")
    @Column(name = "NOMBRE_DISTRITO")
    private String nombreDistrito;
    
    //Relación de muchos distritos a un cantón
    @JoinColumn(name = "ID_CANTON", nullable = false)
    @ManyToOne(optional = false)
    private Canton canton;
    
    //Relación de un distrito a muchos centros
    @OneToMany(mappedBy = "distrito")
    private List<Centro> centros;
}
