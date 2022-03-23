/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.Centro;
import java.util.List;


public interface ICentroService {
    public void guardar (Centro centro);    
    public void eliminar(Integer idCentro);
    public List<Centro> listar();
    public List<Centro> listar(String nombre);
    public Centro obtenerCentro(int idCentro);
}
