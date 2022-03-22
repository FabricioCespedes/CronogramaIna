/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;

import com.ina.ProyectoJavaFabricioJose.domain.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author josea
 */
public interface IMotivoDao extends JpaRepository<Motivo,Integer>{
    
    public Iterable<Motivo> findByJustificacionContains(String justificacion);
    
    
    
    
}
