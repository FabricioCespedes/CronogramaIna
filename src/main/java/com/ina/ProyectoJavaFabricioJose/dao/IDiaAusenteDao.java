/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;

import com.ina.ProyectoJavaFabricioJose.domain.DiaAusente;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author josea
 */
public interface IDiaAusenteDao extends JpaRepository<DiaAusente, Integer> {
    
    @Query(value = "Select * from DIAS_AUSENTES d WHERE ID_PROFESOR =?1", nativeQuery = true)
    public Iterable<DiaAusente> filtrarPorProfesor(Long idProfesor);
    
    
    
    
    
}
