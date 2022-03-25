/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import java.sql.Time;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "CRONOGRAMAS")
public class Cronograma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CRONOGRAMA")
    private int idCronogrograma;

    //Relación de muchos módulos a un cronograma 
    @JoinColumn(name = "ID_MODULO", nullable = false)
    @ManyToOne(optional = false)
    private Modulo modulo;

    //Relación de muchos programas a un cronograma 
    @JoinColumn(name = "ID_PROGRAMA", nullable = false)
    @ManyToOne(optional = false)
    private Programa programa;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "FECHA_INICIO", unique = true)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "FECHA_FIN", unique = true)
    private Date fechaFin;

    @Column(name = "HORAS_DIA")
    @NotEmpty(message = "Las horas por día del cronograma son obligatorias")
    private double horasDia;

    @Column(name = "ESTADO")
    @NotEmpty(message = "El estado del cronograma es obligatorio")
    private String estado;

    @Column(name = "HORA_INICIO")
    @NotEmpty(message = "Las horas por día del cronograma son obligatorias")
    private Time horasInicio;

    @Column(name = "HORA_FIN")
    @NotEmpty(message = "Las horas por día del cronograma son obligatorias")
    private Time horaFin;

}
