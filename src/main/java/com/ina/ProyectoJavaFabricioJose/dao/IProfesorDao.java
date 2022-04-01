/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;

import com.ina.ProyectoJavaFabricioJose.domain.Profesor;
import javax.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IProfesorDao extends JpaRepository<Profesor, Long> {
    
    @Query(value = "Select * from PROFESORES p Where p.ID_CENTRO=?1", nativeQuery = true)
    public Iterable<Profesor> buscarProfesor(int idCentro);
 
    @Query(value = "Select * from PROFESORES p Where p.NOMBRE_PROFESOR LIKE %?1% AND p.ID_CENTRO=?2", nativeQuery = true)
    public Iterable<Profesor> buscarProfesor(String nombre, int idCentro);
   
}
