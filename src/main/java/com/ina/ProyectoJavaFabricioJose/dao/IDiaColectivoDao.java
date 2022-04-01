/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;

import com.ina.ProyectoJavaFabricioJose.domain.DiasAusentesColectivos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IDiaColectivoDao extends JpaRepository<DiasAusentesColectivos, Integer> {
    
    @Query(value = "Select * from DIAS_AUSENTES_COLECTIVOS d WHERE YEAR(FECHA_INICIO) =?1", nativeQuery = true)
    public Iterable<DiasAusentesColectivos> buscarPorAnio(String anio);
}
