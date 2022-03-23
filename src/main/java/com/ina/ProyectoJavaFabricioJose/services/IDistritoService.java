/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.Distrito;
import java.util.List;


public interface IDistritoService {
     public List<Distrito> listar();
    public List<Distrito> listar(String nombreDis);
}
