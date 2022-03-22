/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;

import com.ina.ProyectoJavaFabricioJose.domain.Cronograma;
import com.ina.ProyectoJavaFabricioJose.domain.Programa;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ICronogramaDao extends JpaRepository<Cronograma, Integer> {

    @Procedure(name = "GENERAR_CRONOGRAMA")
    public HashMap generarCronograma(
            @Param("ID_MODULO") int idModulo,
            @Param("ID_PROGRAMA") int idPrograma,
            @Param("HORAS_DIA") double horasDia,
            @Param("HORA_INICIO") Time horaInicio,
            @Param("HORA_FIN") Time horaFin,
            @Param("ESTADO") String estado,
            @Param("ID_CENTRO") int idCentro,
            @Param("FECHA_INICIO") Date fechaInicio,
            @Param("retorno") int retorno);
    
    public Iterable<Cronograma> findByProgramaContains(Programa programa);
        
}
