/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.dao;


import com.ina.ProyectoJavaFabricioJose.domain.Centro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICentroDao extends JpaRepository<Centro, Integer> {
    public Iterable<Centro> findByNombreContains(String nombre);
}
