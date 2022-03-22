/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;

import com.ina.ProyectoJavaFabricioJose.domain.DiaFeriado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author josea
 */
public interface IDiaAusente extends JpaRepository<DiaFeriado, Integer> {
    
    @Query(value = "Select * from DIAS_FERIADOS d WHERE YEAR(FECHA) =?1", nativeQuery = true)
    public Iterable<DiaFeriado> buscarPorAnio(String anio);
    
}
