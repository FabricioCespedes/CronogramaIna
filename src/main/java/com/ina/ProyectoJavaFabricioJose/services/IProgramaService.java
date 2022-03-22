/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.Programa;
import java.util.List;

/**
 *
 * @author josea
 */
public interface IProgramaService {
    
    public void guardar (Programa programa);    
    public void eliminar(Programa programa);
    public List<Programa> listar();
    public List<Programa> listar(String nombrePrograma);
    public Programa obtenerModulo(Integer idPrograma);
    public List<Programa> listarProgramasConCronogramas();
    
}
