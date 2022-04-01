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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "Motivos_De_Ausencias")
public class Motivo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MOTIVO")
    private int idMotivo;

    @Column(name = "JUSTIFICACION", unique = true)
    @NotEmpty(message = "El motivo de la ausencia es obligatoria")
    private String justificacion;

    @OneToMany(mappedBy = "motivo")
    private List<DiaFeriado> feriados;

    @OneToMany(mappedBy = "motivo")
    private List<DiaAusente> diaAusente;

    @OneToMany(mappedBy = "motivo")
    private List<DiasAusentesColectivos> ausentesColectivoses;
}
