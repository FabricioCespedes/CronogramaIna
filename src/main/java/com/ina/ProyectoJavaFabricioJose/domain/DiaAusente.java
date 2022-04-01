/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "DIAS_AUSENTES")
public class DiaAusente implements Serializable {
    
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DIA_AUSENTE")
    private int idDiaAusente;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "FECHA_INICIO", unique = true)
    private Date fechaInicio;
    
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "FECHA_FIN", unique = true)
    private Date fechaFin;
    
    //Relación de muchos dias ausentes a un profesor
    @JoinColumn(name = "ID_PROFESOR", nullable = false)
    @ManyToOne(optional = false)
    private Profesor profesor;
    
    //Relación de muchos dias ausentes a un motivo
    @JoinColumn(name = "ID_MOTIVO", nullable = false)
    @ManyToOne(optional = false)
    private Motivo motivo;
}
