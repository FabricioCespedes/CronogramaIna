/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;

import com.ina.ProyectoJavaFabricioJose.domain.Cronograma;
import com.ina.ProyectoJavaFabricioJose.domain.Programa;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ICronogramaDao extends JpaRepository<Cronograma, Integer> {


    @Query(value = "select * from cronogramas where ID_PROGRAMA =?1", nativeQuery = true)
    public Iterable<Cronograma> listar(Integer idPrograma);

    @Transactional
    @Procedure(procedureName = "GENERAR_CRONOGRAMA", outputParameterName = "FECHA")
    public String generarCronograma(
            @Param("ID_MODULO") int idModulo,
            @Param("ID_PROGRAMA") int idPrograma,
            @Param("ID_PROFESOR") Long idProfesor,
            @Param("HORAS_DIA") Double horasDia,
            @Param("HORA_INICIO") String horaInicio,
            @Param("HORA_FIN") String horaFin,
            @Param("ESTADO") String estado,
            @Param("ID_CENTRO") int idCentro,
            @Param("FECHA_INICIO") Date fechaInicio
    );
    
    @Transactional
    @Procedure(procedureName = "P_INGRESAR_DIAS", outputParameterName = "RETORNO")
    public int ingresarDias(
            @Param("LUNES") boolean lunes,
            @Param("MARTES") boolean martes,
            @Param("MIERCOLES") boolean miercoles,
            @Param("JUEVES") boolean jueves,
            @Param("VIERNES") boolean viernes,
            @Param("SABADO") boolean sabado,
            @Param("ID_MODULO") int idModulo,
            @Param("ID_PROGRAMA") int idPrograma
    );
    
}
