/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.Modulo;
import java.util.List;

/**
 *
 * @author josea
 */
public interface IModuloService {
    
    public void guardar (Modulo modulo);    
    public void eliminar(Modulo modulo);
    public List<Modulo> listar();
    public List<Modulo> listar(String nombreModulo);
    public Modulo obtenerModulo(Integer idModulo);
    
}
