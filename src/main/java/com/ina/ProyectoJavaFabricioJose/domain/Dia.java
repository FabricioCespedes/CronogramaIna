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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "DIAS")
public class Dia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DIA")
    private int idDia;

    @Column(name = "DIA")
    @NotEmpty(message = "El dia es obligatorio")
    private String dia;

    //Relacion de un modulo a muchos cronogramas
    @OneToMany(mappedBy = "diaC")
    private List<CronogramasDias> cronogramaDiasograma;

    //Relacion de un modulo a muchos cronogramas
    @OneToMany(mappedBy = "diaP")
    private List<DiasProgramas> diasProgramas;

}
