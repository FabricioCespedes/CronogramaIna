/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.*;
import java.util.List;


public interface ICronogramaService {
    
    
    public Cronograma generarCronograma(Cronograma cronograma);
    
    public List<Cronograma> listarCronogramas(Programa programa);

    public Cronograma obtenerCronograma(int idCronograma);
     
        
}
