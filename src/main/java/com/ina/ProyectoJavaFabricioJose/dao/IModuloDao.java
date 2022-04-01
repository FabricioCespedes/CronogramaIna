/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;

import com.ina.ProyectoJavaFabricioJose.domain.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IModuloDao extends JpaRepository<Modulo, Integer> {
    
    
    public Iterable<Modulo> findByCodigoContains(String codigo);
    
    @Query(value = "SELECT * FROM MODULOS WHERE ID_MODULO NOT IN (select ID_MODULO from  CRONOGRAMAS WHERE ID_PROGRAMA = ?1)", nativeQuery = true)    
    public Iterable<Modulo> listarModulos(Integer idPrograma);
    
}
