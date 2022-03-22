/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Programas_Dias")
public class DiasProgramas implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "ID_PROGRAMA", nullable = false)
    @ManyToOne(optional = false)
    private Programa programa;

    @Id
    @JoinColumn(name = "ID_DIA", nullable = false)
    @ManyToOne(optional = false)
    private Dia diaP;
}
