/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.Profesor;
import java.util.List;


public interface IProfesorService {
    public int guardar (Profesor profesor);    
    public int eliminar(Profesor profesor);
    public List<Profesor> listar(int idCentro);
    public List<Profesor> listar();
    public List<Profesor> listar(String nombre, int idCentro);
    public Profesor obtenerProfesor(Long idProfesor);
}
