/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author Sergio
 */
@Data
public class PCronograma implements Serializable {

    @NotEmpty(message = "El código del módulo es obligatorio")
    private String codigoM;

    @NotEmpty(message = "El nombre del módulo es obligatorio")
    private String nombreModulo;

    private Integer idModulo;

    @NotEmpty(message = "El código del programa es obligatorio")
    private String codigoP;

    @NotEmpty(message = "El nombre del programa es obligatorio")
    private String nombrePrograma;

    private Integer idPrograma;

    private Long idProfesor;

    @NotEmpty(message = "El Nombre del profesor es obligatorio")
    private String nombreProfesor;

    private Double horasDia;

    @NotEmpty(message="El Nombre del profesor es obligatorio")
    private String horaInicio;

    @NotEmpty(message = "El Nombre del profesor es obligatorio")
    private String horaFin;

    private String estado;
    
    private Date fechaInicio;

    private boolean lunes;

    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;

}
