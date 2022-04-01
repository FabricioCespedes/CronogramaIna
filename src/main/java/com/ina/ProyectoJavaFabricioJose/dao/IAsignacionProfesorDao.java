/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;

import com.ina.ProyectoJavaFabricioJose.domain.AsignacionProfesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Sergio
 */
public interface IAsignacionProfesorDao extends JpaRepository<AsignacionProfesor, Integer>{
    
    @Query(value = "select * from ASIGNACION_PROFESOR where ID_MODULO=?1 and ID_PROGRAMA=?2", nativeQuery = true)
    public Iterable<AsignacionProfesor> listaPorModulos(Integer idModulo, Integer idPrograma);
    
        @Query(value = "select * from ASIGNACION_PROFESOR where ID_PROFESOR=?1", nativeQuery = true)
    public Iterable<AsignacionProfesor> listaPorProfesor(Long idProfesor);
}
