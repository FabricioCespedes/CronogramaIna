/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.Profesor;
import java.util.List;


public interface IProfesorService {
    public void guardar (Profesor profesor);    
    public void eliminar(Profesor profesor);
    public List<Profesor> listar();
    public List<Profesor> listar(String nombre);
    public Profesor obtenerProfesor(Long idProfesor);
}
