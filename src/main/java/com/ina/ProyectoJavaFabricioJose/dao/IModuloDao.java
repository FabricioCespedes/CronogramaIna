/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;

import com.ina.ProyectoJavaFabricioJose.domain.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IModuloDao extends JpaRepository<Modulo, Integer> {
    
    
    public Iterable<Modulo> findByNombreModuloContains(String nombreModulo);
    
}
