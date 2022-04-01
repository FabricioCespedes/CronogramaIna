/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ina.ProyectoJavaFabricioJose.services;

import com.ina.ProyectoJavaFabricioJose.domain.DiasAusentesColectivos;
import java.util.List;


public interface IDiaColectivoService {
    
    public int guardar(DiasAusentesColectivos colectivo);
    public int eliminar(Integer idDia);
    public List<DiasAusentesColectivos> listar();
    public List<DiasAusentesColectivos> listar(String anio);
    public DiasAusentesColectivos obtenerColectivo(int dia);
    
}
