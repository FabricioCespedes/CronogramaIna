/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "DIAS_AUSENTES_COLECTIVOS")
public class DiasAusentesColectivos implements Serializable {
    
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DIA_COLECTIVO")
    private int idDia;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "FECHA_INICIO", unique = true)
    private Date fechaInicio;
    
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "FECHA_FIN", unique = true)
    private Date fechaFin;
    
    //Relación de muchos días colectivos a un centro 
    
    @JoinColumn(name = "ID_CENTRO", nullable = false)
    @ManyToOne(optional = false)
    private Centro centro;
    
    @JoinColumn(name = "ID_MOTIVO", nullable = false)
    @ManyToOne(optional = false)
    private Motivo motivo;
}
