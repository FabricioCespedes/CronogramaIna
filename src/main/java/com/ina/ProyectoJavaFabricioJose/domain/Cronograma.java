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
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CRONOGRAMAS")
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "facturar",
            procedureName = "FACTURAR",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_MODULO", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_PROGRAMA", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "HORAS_DIA", type = Double.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "HORA_INICIO", type = Time.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "HORA_FIN", type = Time.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ESTADO", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID_CENTRO", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.INOUT, name = "FECHA_INICIO", type = Date.class),
                @StoredProcedureParameter(mode = ParameterMode.INOUT, name = "retorno", type = Integer.class)
            }
    )
})
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

    //Relacion de un modulo a muchos cronogramas
    @OneToMany(mappedBy = "cronograma")
    private List<CronogramasDias> cronogramaDias;

    public int getIdCronogrograma() {
        return idCronogrograma;
    }

    public void setIdCronogrograma(int idCronogrograma) {
        this.idCronogrograma = idCronogrograma;
    }

    public Modulo getModuloCronograma() {
        return modulo;
    }

    public void setModuloCronograma(Modulo modulo) {
        this.modulo = modulo;
    }

    public Programa getProgramaCronograma() {
        return programa;
    }

    public void setProgramaCronograma(Programa programa) {
        this.programa = programa;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getHorasDia() {
        return horasDia;
    }

    public void setHorasDia(double horasDia) {
        this.horasDia = horasDia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Time getHorasInicio() {
        return horasInicio;
    }

    public void setHorasInicio(Time horasInicio) {
        this.horasInicio = horasInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public int getRetorno() {
        return retorno;
    }

    public void setRetorno(int retorno) {
        this.retorno = retorno;
    }

    @Transient
    private int retorno;

}
