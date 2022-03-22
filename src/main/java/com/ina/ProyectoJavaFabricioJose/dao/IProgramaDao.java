/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;

import com.ina.ProyectoJavaFabricioJose.domain.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author josea
 */
public interface IProgramaDao extends JpaRepository<Programa, Integer> {
    
    @Query(value = "Select p from PROGRAMAS p WHERE ID_MOTIVO =?1", nativeQuery = true)
    public Iterable<Programa> findByNombreProgramaContains(String nombrePrograma);
    
    
    @Query(value = "SELECT P.ID_PROGRAMA, P.CODIGO, P.NOMBRE_PROGRAMA, P.HORAS_DIA, P.HORA_INICIO, P.HORA_FIN, P.ESTADO,P.ANIO, P.ID_CENTRO, P.GRUPO FROM PROGRAMAS P LEFT OUTER JOIN CRONOGRAMAS C  ON P.ID_PROGRAMA = C.ID_PROGRAMA", nativeQuery = true)
    public Iterable<Programa> listarProgrmasConCronogramas();
    
}
