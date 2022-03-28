/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.domain;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Sergio
 */

@Data
public class ProgramaFecha implements Serializable{
    
    private Programa programa;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String fechaInicio;
    
    private Integer idPro;
}
