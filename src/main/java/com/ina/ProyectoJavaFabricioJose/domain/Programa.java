/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import java.sql.Time;
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
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "PROGRAMAS")
public class Programa implements Serializable {
    
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROGRAMA")
    private int idPrograma;
    
    
    @Column(name = "CODIGO")
    @NotEmpty(message="El código del programa es obligatorio")
    private String codigo;
    
    @Column(name = "NOMBRE_PROGRAMA")
    @NotEmpty(message="El nombre del programa es obligatorio")
    private String nombrePrograma;
    
    
    @Column(name = "HORAS_DIA")
    private double horasDia;
    
    @Column(name = "HORA_INICIO")
    private String horaInicio;
    
    @Column(name = "HORA_FIN")
    @DateTimeFormat(pattern = "HH:mm")
    private String horaFin;
    
    @Column(name = "ESTADO")
    @NotEmpty(message="El estado del programa es obligatorio")
    private String estado;
    
    @Column(name = "ANIO")
    private int anio;
    
    //Relación de muchos programas a un centro
    @JoinColumn(name = "ID_CENTRO", nullable = false)
    @ManyToOne(optional = false)
    private Centro centro;
    
    @Column(name = "GRUPO")
    private int grupo;

    //Relacion de un programa a muchas asignaciones de programa
    @OneToMany(mappedBy = "programa")
    private List<AsignacionProfesor> asignacionProfesor;
    
    //Relacion de un programa a muchos cronogramas
    @OneToMany(mappedBy = "programa")
    private List<Cronograma> cronograma;
    
     //Relacion de un modulo a muchos cronogramas
    @OneToMany(mappedBy = "programa")
    private List<DiasProgramas> diasProgramas;

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public double getHorasDia() {
        return horasDia;
    }

    public void setHorasDia(double horasDia) {
        this.horasDia = horasDia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public List<AsignacionProfesor> getAsignacionProfesor() {
        return asignacionProfesor;
    }

    public void setAsignacionProfesor(List<AsignacionProfesor> asignacionProfesor) {
        this.asignacionProfesor = asignacionProfesor;
    }

    public List<Cronograma> getCronograma() {
        return cronograma;
    }

    public void setCronograma(List<Cronograma> cronograma) {
        this.cronograma = cronograma;
    }

    public List<DiasProgramas> getDiasProgramas() {
        return diasProgramas;
    }

    public void setDiasProgramas(List<DiasProgramas> diasProgramas) {
        this.diasProgramas = diasProgramas;
    }
    
    
}
